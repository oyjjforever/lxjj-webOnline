package com.lxjj.wo.dao;

import com.lxjj.wo.entity.User;
import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User> {
    User queryUserByLogin(User user);
}
