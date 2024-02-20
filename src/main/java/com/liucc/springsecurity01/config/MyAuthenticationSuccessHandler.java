package com.liucc.springsecurity01.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        Object credentials = authentication.getCredentials();
        Object principal = authentication.getPrincipal();
        String name = authentication.getName();
        log.info("credentials:{}", credentials);
        log.info("principal:{}", principal);
        log.info("name:{}", name);


        Map<String, Object> result = new HashMap<>();
        result.put("code", "0"); // 成功
        result.put("message", "认证成功");
        result.put("data", principal);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(result);
    }
}
