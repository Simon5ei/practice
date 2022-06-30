package com.practice.testmybatis.controller;

import com.practice.testmybatis.service.LoginService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
    @Autowired
    private LoginService Login;

    @RequestMapping("/login")
    @ResponseBody
    public String ShowAddRes(@Param("name") String name, @Param("pswd") String pswd) {
        if(Login.login(name,pswd))
            return "Success";
        else
            return "Something Wrong";
    }
}
