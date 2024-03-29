package com.liucc.springsecurity01.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liucc.springsecurity01.entity.User;
import com.liucc.springsecurity01.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @ClassName: DBUserDetailsManager
 * @Description: TODO (用一句话描述该文件做什么)
 * @author: liuchuangchuang
 * @date: 2024/2/20 17:15
 * @version: V1.0
 */

@Component
public class DBUserDetailsManager implements UserDetailsManager, UserDetailsPasswordService {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        } else {
            // 1、给用户进行资源授权
//            Collection<GrantedAuthority> authorities = new ArrayList<>();
//            authorities.add(()->"USER_LIST");
//            authorities.add(()->"USER_ADD");
//            return new org.springframework.security.core.userdetails.User(
//                    user.getUsername(),
//                    user.getPassword(),
//                    user.getEnabled(),
//                    true, //用户账号是否过期
//                    true, //用户凭证是否过期
//                    true, //用户是否未被锁定
//
////                    authorities) //权限列表
//                    ;
            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getUsername())
                    .password(user.getPassword())
                    // 1、给用户配置资源权限
//                    .authorities(authorities)
                    // 2、给用户配置管理员角色
                    .roles("ADMIN")
                    .build();
        }
    }

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        return null;
    }

    @Override
    public void createUser(UserDetails user) {

    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return false;
    }
}
