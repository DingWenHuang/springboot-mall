package com.wenhuang.springbootmall.service.impl;

import com.wenhuang.springbootmall.dao.UserDao;
import com.wenhuang.springbootmall.dto.UserRegisterRequest;
import com.wenhuang.springbootmall.model.User;
import com.wenhuang.springbootmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        return userDao.createUser(userRegisterRequest);
    }

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }
}
