package com.ssm.service.impl;

import com.ssm.dao.UserDao;
import com.ssm.dao.UserMoodPraiseRelDao;
import com.ssm.entity.Mood;
import com.ssm.dao.MoodDao;
import com.ssm.entity.UserMoodPraiseRel;
import com.ssm.mq.MoodProducer;
import com.ssm.service.MoodService;
import com.ssm.service.UserService;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.jms.Destination;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * (Mood)表服务实现类
 *
 * @author makejava
 * @since 2021-08-21 16:31:26
 */
@Service("moodService")
public class MoodServiceImpl implements MoodService {
    @Resource
    private MoodDao moodDao;
    @Resource
    private UserDao userDao;
    private UserService userService;
    @Resource
    private UserMoodPraiseRelDao userMoodPraiseRelDao;
    @Resource
    private MoodProducer moodProducer;
    @Resource
    private RedisTemplate redisTemplate;

    public void setMoodDao(MoodDao moodDao) {
        this.moodDao = moodDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setUserMoodPraiseRelDao(UserMoodPraiseRelDao userMoodPraiseRelDao) {
        this.userMoodPraiseRelDao = userMoodPraiseRelDao;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Mood queryById(String id) {
        return this.moodDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Mood> queryAllByLimit(int offset, int limit) {
        return this.moodDao.queryAllByLimit(offset, limit);
    }

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    @Override
    public List<Mood> queryAll() {
        return moodDao.queryAll();
    }

    /**
     * 新增数据
     *
     * @param mood 实例对象
     * @return 实例对象
     */
    @Override
    public Mood insert(Mood mood) {
        this.moodDao.insert(mood);
        return mood;
    }

    /**
     * 修改数据
     *
     * @param mood 实例对象
     * @return 实例对象
     */
    @Override
    public Mood update(Mood mood) {
        this.moodDao.update(mood);
        return this.queryById(mood.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.moodDao.deleteById(id) > 0;
    }

    @Override
    public boolean praiseMood(String userId, String moodId) {
        UserMoodPraiseRel userMoodPraiseRel = new UserMoodPraiseRel();
        userMoodPraiseRel.setUserId(userId);
        userMoodPraiseRel.setMoodId(moodId);
        userMoodPraiseRelDao.save(userMoodPraiseRel);
        Mood mood = moodDao.queryById(moodId);
        mood.setPraiseNum(mood.getPraiseNum() + 1);
        moodDao.update(mood);

        return Boolean.TRUE;
    }

    private static final String PRAISE_HASH_KEY = "ssmproject.ssm.mood.id.list.key";

    private static Destination destination = new ActiveMQQueue("ay.queue.high.concurrency.praise");

    @Override
    public boolean praiseMoodForRedis(String userId, String moodId) {

        Mood mood = new Mood();
        mood.setUserId(userId);
        mood.setId(moodId);
        moodProducer.sendMessage(destination, mood);




        // 存放到集合中
        //redisTemplate.opsForSet().add(PRAISE_HASH_KEY, moodId);
        //redisTemplate.opsForSet().add(moodId, userId);
        return false;
    }

    @Override
    public List<Mood> findAllForRedis() {
        System.out.println("==>进入了findAllForRedis方法");
        List<Mood> moodList = moodDao.queryAll();
        if (CollectionUtils.isEmpty(moodList))
            return Collections.EMPTY_LIST;

        List<Mood> moods = new ArrayList<>();
        for (Mood mood : moodList) {
            Mood mood1 = new Mood();
            mood1.setId(mood.getId());
            mood1.setUserId(mood.getUserId());
            mood1.setPraiseNum(mood.getPraiseNum()+redisTemplate.opsForSet().size(mood.getId()).intValue());
            mood1.setPublishTime(mood.getPublishTime());
            mood1.setContent(mood.getContent());
            mood1.setAccount(mood.getAccount());
            moods.add(mood1);
        }
        return moods;
    }
}
