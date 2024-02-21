package com.liucc.springsecurity01.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: MyAuthenticationEntryPoint
 * @Description: TODO (用一句话描述该文件做什么)
 * @author: liuchuangchuang
 * @date: 2024/2/21 14:06
 * @version: V1.0
 */
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String localizedMessage = authException.getLocalizedMessage();
        Map<String, Object> result = new HashMap<>();
        result.put("code", "-1"); // 认证失败
        result.put("message", "您暂未认证，请前去登录"); // 用户未认证，拒绝其访问资源，提示用户前去登录
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(result);
    }
}
