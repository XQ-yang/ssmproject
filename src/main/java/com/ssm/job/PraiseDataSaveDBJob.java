package com.ssm.job;

import com.ssm.entity.Mood;
import com.ssm.entity.UserMoodPraiseRel;
import com.ssm.service.MoodService;
import com.ssm.service.UserMoodPraiseRelService;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

/**
 * @author: 小强
 * @date: 2021/8/29 0029
 * @tool: IntelliJ IDEA
 * @words: Be more professional every day!
 */
@Component
@Configurable
@EnableScheduling
public class PraiseDataSaveDBJob {

    @Resource
    private RedisTemplate redisTemplate;
    private static final String PRAISE_HASH_KEY = "ssmproject.ssm.mood.id.list.key";

    @Resource
    private UserMoodPraiseRelService userMoodPraiseRelService;
    @Resource
    private MoodService moodService;

    //每分钟执行一次，在真实项目中，可以把定时器的执行计划时间设置长一点，如每天晚上凌晨4点执行
    @Scheduled(cron = "0 0/1 * * * ?")
    public void savePraiseDataToDB2(){
        //为方便观察，控制台输出时间
        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.err.println("现在的时间是："+ sf.format(date));
        System.err.println("==>进入了savePraiseDataToDB2方法******");
        //step1:在redis中缓存所有被点赞的说说
        Set<String> moods = redisTemplate.opsForSet().members(PRAISE_HASH_KEY);

        if (CollectionUtils.isEmpty(moods)) {
            return;
        }

        for (String moodId : moods) {
            if (redisTemplate.opsForSet().members(moodId) == null) {
                continue;
            } else {
                //step2:在redis缓存中，通过说说id获取所有点赞的用户id列表
                Set<String> userIds = redisTemplate.opsForSet().members(moodId);
                if (CollectionUtils.isEmpty(userIds)) {
                    continue;
                } else {
                    //step3：通过循环来保存mood_id和user_id的关联关系到MySQL数据库
                    for (String userId : userIds) {
                        UserMoodPraiseRel userMoodPraiseRel = new UserMoodPraiseRel();
                        userMoodPraiseRel.setMoodId(moodId);
                        userMoodPraiseRel.setUserId(userId);
                        //保存说说与用户的关联关系
                        userMoodPraiseRelService.save(userMoodPraiseRel);
                    }
                    Mood mood = moodService.queryById(moodId);
                    //step4：更新说说点赞数量，数量=redis点赞数量+数据库点赞数量
                    mood.setPraiseNum(mood.getPraiseNum() + redisTemplate.opsForSet().size(moodId).intValue());
                    moodService.update(mood);
                    //step5：清除redis缓存中的数据
                    redisTemplate.delete(moodId);
                }
            }
        }

        //step6：清除redis缓存中的数据
        redisTemplate.delete(PRAISE_HASH_KEY);
    }
}
