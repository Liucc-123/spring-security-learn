package com.liucc.springsecurity01.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        String localizedMessage = exception.getLocalizedMessage();
        Map<String, Object> result = new HashMap<>();
        result.put("code", "-1"); // 认证失败
        result.put("message", localizedMessage);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(result);
    }
}
