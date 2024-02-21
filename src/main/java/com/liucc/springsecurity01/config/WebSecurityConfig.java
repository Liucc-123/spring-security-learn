package com.liucc.springsecurity01.config;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: WebSecurityConfig
 * @Description: TODO (用一句话描述该文件做什么)
 * @author: liuchuangchuang
 * @date: 2024/2/20 17:17
 * @version: V1.0
 */
@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //authorizeRequests()：开启授权保护
        //anyRequest()：对所有请求开启授权保护
        //authenticated()：已认证请求会自动被授权
        http.authorizeRequests(authorize -> authorize
                        // 用户-权限-资源
//                .requestMatchers("/user/list").hasAuthority("USER_LIST")
//                .requestMatchers("/user/add").hasAuthority("USER_ADD")
                // 用户-角色-资源
                .requestMatchers("/user/**").hasRole("ADMIN")
                .anyRequest()  // 对所有请求开启授权保护
                .authenticated() // 已认证的请求会被自动授权
        );
//                .formLogin(withDefaults());//表单授权方式
        http.formLogin(form -> {
            form.loginPage("/login").permitAll() //登录页面无需授权即可访问
                    .usernameParameter("username") //自定义表单用户名参数，默认是username
                    .passwordParameter("password") //自定义表单密码参数，默认是password
                    .failureUrl("/login?error")//登录失败的返回地址
                    .successHandler(new MyAuthenticationSuccessHandler()) //注册自定义的认证成功处理器
                    .failureHandler(new MyAuthenticationFailureHandler())//注册自定义的认证失败处理器
            ;
        });
//                .httpBasic(withDefaults());// 浏览器基本授权方式
        // 登出管理
        http.logout(logout -> {
            logout.logoutSuccessHandler(new MyLogoutSuccessHandler());
        });

        // 会话管理
        http.sessionManagement(session -> {
            session.maximumSessions(1).expiredSessionStrategy(new MySessionInformationExpiredStrategy());
        });
        // 错误处理
        http.exceptionHandling(exception -> {
            exception.authenticationEntryPoint(new MyAuthenticationEntryPoint());//请求未认证
            // 匿名内部类方式
//            exception.accessDeniedHandler(new AccessDeniedHandler() { // 请求未授权
//                @Override
//                public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
//
//                    Map<String, Object> result = new HashMap<>();
//                    result.put("code", -1);
//                    result.put("message", "没有权限");
//                    //转换成json字符串
//                    String json = JSON.toJSONString(result);
//                    // 响应错误信息给前端
//                    response.setContentType("application/json;charset=UTF-8");
//                    response.getWriter().println(json);
//                }
//            });
            // lambda表达式
            exception.accessDeniedHandler((req, res, ex) -> { // 请求未授权
                Map<String, Object> result = new HashMap<>();
                result.put("code", -1);
                result.put("message", "没有权限");
                //转换成json字符串
                String json = JSON.toJSONString(result);
                // 响应错误信息给前端
                res.setContentType("application/json;charset=UTF-8");
                res.getWriter().println(json);
            });
        });

        //关闭csrf攻击防御
        http.csrf((csrf) -> {
            csrf.disable();
        });

        return http.build();
    }
}
