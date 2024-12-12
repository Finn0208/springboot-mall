package com.finn.springbootmall.dao;

import com.finn.springbootmall.dto.UserRegisteRequest;
import com.finn.springbootmall.model.User;

public interface UserDao {

    User getUserByEmail(String email);
    User getUserById(Integer userId);
    Integer createUser(UserRegisteRequest userRegisteRequest);
}
