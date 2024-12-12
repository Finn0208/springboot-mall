package com.finn.springbootmall.service.impl;

import com.finn.springbootmall.dao.UserDao;
import com.finn.springbootmall.dto.UserRegisteRequest;
import com.finn.springbootmall.model.User;
import com.finn.springbootmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public Integer register(UserRegisteRequest userRegisteRequest) {
        return userDao.createUser(userRegisteRequest);
    }
}
