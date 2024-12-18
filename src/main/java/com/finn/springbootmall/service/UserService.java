package com.finn.springbootmall.service;

import com.finn.springbootmall.dto.UserLoginRequest;
import com.finn.springbootmall.dto.UserRegisterRequest;
import com.finn.springbootmall.model.User;

public interface UserService {

    User getUserById(Integer userId);
    Integer register(UserRegisterRequest userRegisteRequest);
    User login(UserLoginRequest userLoginRequest);
}
