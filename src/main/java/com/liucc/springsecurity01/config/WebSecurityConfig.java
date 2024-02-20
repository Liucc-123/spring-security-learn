package com.liucc.springsecurity01.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

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
        http
                .authorizeRequests(authorize -> authorize.anyRequest().authenticated())
//                .formLogin(withDefaults());//表单授权方式
                .formLogin(form -> {
                    form.loginPage("/login").permitAll() //登录页面无需授权即可访问
                            .usernameParameter("username") //自定义表单用户名参数，默认是username
                            .passwordParameter("password") //自定义表单密码参数，默认是password
                            .failureUrl("/login?error"); //登录失败的返回地址
                });
//                .httpBasic(withDefaults());// 浏览器基本授权方式

        //关闭csrf攻击防御
//        http.csrf((csrf) -> {
//            csrf.disable();
//        });

        return http.build();
    }
}
