package com.lxjj.wo.service.Impl;

import com.lxjj.wo.dao.UserMapper;
import com.lxjj.wo.entity.User;
import com.lxjj.wo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User findUserByUserCodePassword(String userCode, String password) {
        User user = new User();
        user.setAccount(userCode);
        user.setPassword(password);
        return userMapper.queryUserByLogin(user);
    }
}
