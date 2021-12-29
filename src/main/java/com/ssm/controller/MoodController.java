package com.ssm.controller;

import com.ssm.entity.Mood;
import com.ssm.service.MoodService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

/**
 * (Mood)表控制层
 *
 * @author makejava
 * @since 2021-08-21 17:39:43
 */
@Controller
@RequestMapping("mood")
public class MoodController {
    /**
     * 服务对象
     */
    @Resource
    private MoodService moodService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @RequestMapping("/selectOne")
    public Mood selectOne(String id) {
        return this.moodService.queryById(id);
    }


    @RequestMapping("/findAll")
    public String findAll(Model model) {
        List<Mood> moods = moodService.queryAll();
        model.addAttribute("moods", moods);
        return "mood";
    }

    @RequestMapping("/{moodId}/praise/{userId}")
    public String praise(Model model, @PathVariable("moodId") String moodId, @PathVariable("userId") String userId) {
        System.out.println("==>进来了！");
        boolean isPraise = moodService.praiseMood(userId, moodId);

        List<Mood> moods = moodService.queryAll();
        model.addAttribute("moods",moods);
        model.addAttribute("isPraise",isPraise);
        return "redirect:/mood/findAll";

    }


    @RequestMapping("/{moodId}/praiseForRedis/{userId}")
    public String praiseForRedis(Model model,@PathVariable("moodId")String moodId,@PathVariable("userId")String userId){
        Random random = new Random();
        userId = random.nextInt(100) + "";

        boolean isPraise = moodService.praiseMoodForRedis(userId, moodId);

        List<Mood> moods = moodService.findAllForRedis();

        model.addAttribute("moods", moods);
        model.addAttribute("isPraise",isPraise);
        return "mood";
    }

}
