package com.wenhuang.springbootmall.service;

import com.wenhuang.springbootmall.dto.UserLoginRequest;
import com.wenhuang.springbootmall.dto.UserRegisterRequest;
import com.wenhuang.springbootmall.model.User;

public interface UserService {

    Integer register(UserRegisterRequest userRegisterRequest);

    User getUserById(Integer userId);

    User login(UserLoginRequest userLoginRequest);
}
