package com.liucc.springsecurity01.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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
@Slf4j
public class IndexController {

    @GetMapping("/")
    public String index() {
        SecurityContext context = SecurityContextHolder.getContext();// 获取安全上下文
        Authentication authentication = context.getAuthentication();//获取认证对象
        String name = authentication.getName();//用户名
        Object principal = authentication.getPrincipal();//用户身份标识（详细信息），
        Object credentials = authentication.getCredentials();//用户凭证
        log.info("credentials:{}", credentials);
        log.info("principal:{}", principal);
        log.info("name:{}", name);
        return "index";
    }

}
