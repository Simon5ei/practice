package com.practice.testmybatis.service;

import com.practice.testmybatis.component.redis;
import com.practice.testmybatis.domain.User;
import com.practice.testmybatis.mapper.UserNameMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private redis Redis;
    @Autowired
    private UserNameMapper QueryNameMapper;
    public Boolean login(String name,String pwsd){
        User u=QueryNameMapper.queryNameMapper(name);
        if(u.getPswd().equals(pwsd)){
            Redis.set(u.getIduser().toString(),"1",30);
            return true;
        }
        return false;
    }
}
