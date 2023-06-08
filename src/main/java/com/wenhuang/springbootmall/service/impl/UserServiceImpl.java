package com.wenhuang.springbootmall.service.impl;

import com.wenhuang.springbootmall.dao.UserDao;
import com.wenhuang.springbootmall.dto.UserLoginRequest;
import com.wenhuang.springbootmall.dto.UserRegisterRequest;
import com.wenhuang.springbootmall.model.User;
import com.wenhuang.springbootmall.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.server.ResponseStatusException;

import java.nio.charset.StandardCharsets;

@Component
public class UserServiceImpl implements UserService {

    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        //check if email has already been registered or not
        User user = userDao.getUserByEmail(userRegisterRequest.getEmail());

        if (user != null) {
            log.warn("email {} has already been registered!", userRegisterRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        //MD5 hashed password
        String hashedPassword = DigestUtils.md5DigestAsHex(userRegisterRequest.getPassword().getBytes());
        userRegisterRequest.setPassword(hashedPassword);

        //create a new user
        return userDao.createUser(userRegisterRequest);
    }

    @Override
    public User login(UserLoginRequest userLoginRequest) {
        User user = userDao.getUserByEmail(userLoginRequest.getEmail());

        //check email
        if (user == null) {
            log.warn("this email {} has not been registered", userLoginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        //MD5 hashed password
        String hashedPassword = DigestUtils.md5DigestAsHex(userLoginRequest.getPassword().getBytes());
        userLoginRequest.setPassword(hashedPassword);

        //check password
        if (user.getPassword().equals(hashedPassword)) {
            return user;
        } else {
            log.warn("password for email {} is not correct", userLoginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
