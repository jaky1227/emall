package com.emall.service.impl;

import com.emall.model.User;
import com.emall.dao.UserMapper;
import com.emall.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    //验证用户名和密码
    @Override
    public User getUser(String username, String password) {
        return userMapper.getUser(username, password);
    }
}
