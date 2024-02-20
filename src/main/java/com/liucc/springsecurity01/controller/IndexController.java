package com.liucc.springsecurity01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ClassName: IndexController
 * @Description: TODO (用一句话描述该文件做什么)
 * @author: liuchuangchuang
 * @date: 2024/2/20 17:06
 * @version: V1.0
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

}
