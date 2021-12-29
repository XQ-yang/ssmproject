package com.ssm.controller;

import com.ssm.entity.User;
import com.ssm.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2021-08-21 17:01:35
 */
@Controller
@RequestMapping("user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ResponseBody
    @RequestMapping("/selectOne/{id}")
    public User selectOne(@PathVariable String id) {
        return userService.queryById(id);
    }

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }

}
