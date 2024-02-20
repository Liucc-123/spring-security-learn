package com.liucc.springsecurity01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ClassName: LoginController
 * @Description: TODO (用一句话描述该文件做什么)
 * @author: liuchuangchuang
 * @date: 2024/2/20 17:39
 * @version: V1.0
 */
@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
