package com.liucc.springsecurity01.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liucc.springsecurity01.entity.User;
import com.liucc.springsecurity01.mapper.UserMapper;
import com.liucc.springsecurity01.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @ClassName: UserServiceImpl
 * @Description: TODO (用一句话描述该文件做什么)
 * @author: liuchuangchuang
 * @date: 2024/2/20 17:14
 * @version: V1.0
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
