package com.practice.testmybatis.controller;

import com.practice.testmybatis.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @Autowired
    private HelloService Showinfo;
    @RequestMapping("/info/{userID}")
    @ResponseBody
    public String FuncShowID(@PathVariable("userID") Integer userID) {
        return Showinfo.showInfo(userID);
    }
}