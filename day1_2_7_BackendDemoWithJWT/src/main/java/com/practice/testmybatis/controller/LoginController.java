package com.practice.testmybatis.controller;

import com.practice.testmybatis.auth.PassToken;
import com.practice.testmybatis.auth.UserLoginToken;
import com.practice.testmybatis.component.redis;
import com.practice.testmybatis.domain.User;
import com.practice.testmybatis.mapper.UserNameMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.locks.ReentrantLock;

@RestController
public class LoginController {
    @Autowired
    private redis Redis;
    @Autowired
    private UserNameMapper queryNameMapper;
    @PassToken
    @RequestMapping("/user/login")
    public String login(@RequestBody User user) throws JSONException {
        JSONObject jsonObject=new JSONObject();
        User userForBase=queryNameMapper.queryNameMapper(user.getName());
        if(userForBase==null){
            jsonObject.put("message","登录失败,用户不存在");
        }else {
            if (!userForBase.getPswd().equals(user.getPswd())){
                jsonObject.put("message","登录失败,密码错误");
            }else {
                String token = User.getToken(user);
                System.out.println(token);
                jsonObject.put("token", token);
                jsonObject.put("user", userForBase);
            }
        }
        return jsonObject.toString();
    }
    private final ReentrantLock lock = new ReentrantLock();
    private String token;
    @UserLoginToken
    @GetMapping("/getmessage")
    public String getMessage(HttpServletRequest httpServletRequest) throws InterruptedException {
        token = httpServletRequest.getHeader("token");
        if (Redis.get(User.getIduserToken(token))!=null){
            return "慢点";
        }
        //dfhlksdfal;df
        Redis.set(User.getIduserToken(token),"1",30);
        return User.getIduserToken(token);
    }

}
