package com.practice.testmybatis.service;

import com.practice.testmybatis.component.redis;
import com.practice.testmybatis.domain.User;
import com.practice.testmybatis.mapper.UserIDMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloService {
    @Autowired
    private UserIDMapper userIDMapper;
    @Autowired
    private redis Redis;
    public String showInfo(Integer userID) {
        User u=userIDMapper.queryUserIDMapper(userID);
        if(Redis.get(u.getIduser().toString())==null) {
            return "Plz Login!";
        }
        if(Redis.get(u.getIduser().toString()).equals("1")){
            return "Hello!";
        }
        else{ return "???";}
    }
}
