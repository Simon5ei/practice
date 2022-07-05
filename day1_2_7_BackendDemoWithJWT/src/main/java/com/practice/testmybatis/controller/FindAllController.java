package com.practice.testmybatis.controller;

import com.practice.testmybatis.domain.User;
import com.practice.testmybatis.service.FindAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
@Controller
public class FindAllController {
    @Autowired
    private FindAllService ShowUser;
    @RequestMapping("/query")
    @ResponseBody
    public List<User> queryUserList(){
         List<User> users = ShowUser.showUser();
         return users;
    }
}
