package com.wenhuang.springbootmall.dao;

import com.wenhuang.springbootmall.dto.UserRegisterRequest;
import com.wenhuang.springbootmall.model.User;

public interface UserDao {

    Integer createUser(UserRegisterRequest userRegisterRequest);

    User getUserById(Integer userId);
}
