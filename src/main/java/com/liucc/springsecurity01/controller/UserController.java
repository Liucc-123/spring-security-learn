package com.liucc.springsecurity01.controller;

import com.liucc.springsecurity01.entity.User;
import com.liucc.springsecurity01.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName: UserController
 * @Description: TODO (用一句话描述该文件做什么)
 * @author: liuchuangchuang
 * @date: 2024/2/20 17:15
 * @version: V1.0
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Resource
    public UserService userService;

    @GetMapping("/list")
    public List<User> getList(){
        return userService.list();
    }

    @PostMapping("/add")
    public void addUser(){
        log.info("模拟添加用户成功");
    }
}
