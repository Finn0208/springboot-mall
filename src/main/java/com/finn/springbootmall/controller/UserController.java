package com.finn.springbootmall.controller;

import com.finn.springbootmall.dto.UserRegisteRequest;
import com.finn.springbootmall.model.User;
import com.finn.springbootmall.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users/register")
    public ResponseEntity<User> register(@RequestBody @Valid UserRegisteRequest userRegisteRequest){
        Integer userId = userService.register( userRegisteRequest);

        User user = userService.getUserById(userId);
        return ResponseEntity.status(HttpStatus.OK).body(user);

    }
}
