package com.lxjj.wo.service;

import com.lxjj.wo.entity.User;

public interface UserService {
    User findUserByUserCodePassword(String userCode, String password);
}
