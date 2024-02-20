package com.liucc.springsecurity01.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liucc.springsecurity01.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @InterfaceName: UserMapper
 * @Description: TODO (用一句话描述该文件做什么)
 * @author: liuchuangchuang
 * @date: 2024/2/20 17:12
 * @version: V1.0
 */

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
