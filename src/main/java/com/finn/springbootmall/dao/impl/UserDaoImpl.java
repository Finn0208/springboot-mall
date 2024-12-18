package com.finn.springbootmall.dao.impl;

import com.finn.springbootmall.dao.UserDao;
import com.finn.springbootmall.dto.UserRegisterRequest;
import com.finn.springbootmall.model.User;
import com.finn.springbootmall.rowmapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public User getUserById(Integer userId) {
        String sql = "SELECT user_id, email, password, created_date, last_modified_date " +
                "FROM `user` WHERE user_id = :userId"; // 修正 ": userId" -> ":userId"

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId); // 確保鍵與 SQL 佔位符一致

        List<User> userList = jdbcTemplate.query(sql, map, new UserRowMapper());

        if (userList.size() > 0) {
            return userList.get(0); // 返回第一個用戶
        } else {
            return null; // 如果無結果，返回 null
        }
    }

    @Override
    public User getUserByEmail(String email) {
        String sql = "SELECT user_id, email, password, created_date, last_modified_date " +
                "FROM `user` WHERE email = :email"; // 修正 ": userId" -> ":userId"

        Map<String, Object> map = new HashMap<>();
        map.put("email", email); // 確保鍵與 SQL 佔位符一致

        List<User> userList = jdbcTemplate.query(sql, map, new UserRowMapper());

        if (userList.size() > 0) {
            return userList.get(0); // 返回第一個用戶
        } else {
            return null; // 如果無結果，返回 null
        }

    }

    @Override
    public Integer createUser(UserRegisterRequest userRegisteRequest) {
        String sql = "INSERT INTO `user`(email,password,created_date,last_modified_date)"+
                "VALUES ( :email, :password, :createdDate, :lastModifiedDate)";

        Map<String,Object>map = new HashMap<>();
        map.put("email",userRegisteRequest.getEmail());
        map.put("password", userRegisteRequest.getPassword());

        Date now = new Date();
        map.put("createdDate",now);
        map.put("lastModifiedDate",now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(sql,new MapSqlParameterSource(map),keyHolder);
        int userId = keyHolder.getKey().intValue();

        return userId;
    }


}
