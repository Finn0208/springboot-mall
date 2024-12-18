package com.finn.springbootmall.dao;

import com.finn.springbootmall.dto.UserRegisterRequest;
import com.finn.springbootmall.model.User;

public interface UserDao {

    User getUserByEmail(String email);
    User getUserById(Integer userId);
    Integer createUser(UserRegisterRequest userRegisteRequest);
}
