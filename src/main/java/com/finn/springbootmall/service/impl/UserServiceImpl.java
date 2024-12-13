package com.finn.springbootmall.service.impl;

import com.finn.springbootmall.dao.UserDao;
import com.finn.springbootmall.dto.UserLoginRequest;
import com.finn.springbootmall.dto.UserRegisteRequest;
import com.finn.springbootmall.model.User;
import com.finn.springbootmall.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.server.ResponseStatusException;

@Component
public class UserServiceImpl implements UserService {

    private static final Logger log =  LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public Integer register(UserRegisteRequest userRegisteRequest) {
        // 檢查註冊的 Email
        User user = userDao.getUserByEmail(userRegisteRequest.getEmail());

        if(user != null){
            log.warn("該 email {} 已經被註冊 {} ", userRegisteRequest.getEmail(),"Finn");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        //使用 MD5生成密碼的雜湊值
        String hashedPassword = DigestUtils.md5DigestAsHex(userRegisteRequest.getPassword().getBytes());
        userRegisteRequest.setPassword(hashedPassword);

        //創建帳號
        return userDao.createUser(userRegisteRequest);
    }

    @Override
    public User login(UserLoginRequest userLoginRequest) {
        User user = userDao.getUserByEmail(userLoginRequest.getEmail());

        //檢查user是否存在
        if(user == null){
            log.warn("該 email {} 尚未註冊", userLoginRequest.getEmail());
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        //使用 MD5生成密碼的雜湊值
        String hashedPassword = DigestUtils.md5DigestAsHex(userLoginRequest.getPassword().getBytes());

        //比較密碼
        if (user.getPassword().equals(hashedPassword)){
            return user;
        }else{
            log.warn("email {} 密碼不正確", userLoginRequest.getEmail());
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
