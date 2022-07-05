package com.practice.testmybatis.service;

import com.practice.testmybatis.domain.User;
import com.practice.testmybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllService {
    @Autowired
    private UserMapper userMapper;

    public List<User> showUser() {
        return userMapper.queryUserList();
    }
}