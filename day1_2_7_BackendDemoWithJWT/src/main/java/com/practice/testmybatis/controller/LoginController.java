package com.practice.testmybatis.controller;

import com.practice.testmybatis.auth.PassToken;
import com.practice.testmybatis.auth.UserLoginToken;
import com.practice.testmybatis.domain.User;
import com.practice.testmybatis.mapper.UserNameMapper;
import com.practice.testmybatis.service.LoginService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class LoginController {
    @Autowired
    private LoginService Login;
    @Autowired
    private UserNameMapper QueryNameMapper;
    @PassToken
    @RequestMapping("/user/login")
    public String login(@RequestBody User user) throws JSONException {
        JSONObject jsonObject=new JSONObject();
        User userForBase=QueryNameMapper.queryNameMapper(user.getName());
        if(userForBase==null){
            //System.out.println("1");
            jsonObject.put("message","登录失败,用户不存在");
            return jsonObject.toString();
        }else {
            if (!userForBase.getPswd().equals(user.getPswd())){
                //System.out.println("2");
                jsonObject.put("message","登录失败,密码错误");
                return jsonObject.toString();
            }else {
                //System.out.println("3");
                String token = User.getToken(user);
                System.out.println(token);
                jsonObject.put("token", token);
                jsonObject.put("user", userForBase);
                return jsonObject.toString();
            }
        }
    }
    @UserLoginToken
    @GetMapping("/getmessage")
    public String getMessage(HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("token");
        return User.getIduserToken(token);
    }
}
