package com.finn.springbootmall.service;

import com.finn.springbootmall.dto.UserRegisteRequest;
import com.finn.springbootmall.model.User;

public interface UserService {

    User getUserById(Integer userId);
    Integer register(UserRegisteRequest userRegisteRequest);

}
