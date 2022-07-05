package com.practice.testhttpclient.controller;

import com.practice.testhttpclient.model.User;
import com.practice.testhttpclient.service.testService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class testController {
    private static final Logger logger = LoggerFactory.getLogger(testService.class);
    @Autowired
    private testService testUser;
//    @RequestMapping("/test")
//    @ResponseBody
//    public String bind(@RequestBody User user){
//        return testUser.BindWithModel(user).getNikeName();
//    }
    @RequestMapping("/getinfo")
    @ResponseBody
    public String doGet(@RequestBody User user){
        try {
            return testUser.Get("http://localhost:8080/info",user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @RequestMapping("/postinfo")
    @ResponseBody
    public String doPosti(@RequestBody User user){
        try {
            return testUser.Post("http://localhost:8080/info",user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @RequestMapping("/posttag")
    @ResponseBody
    public String doPostt(@RequestBody User user){
        try {
            return testUser.Post("http://localhost:8080/tag",user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @RequestMapping("/postfollow")
    @ResponseBody
    public String doPostf(@RequestBody User user){
        try {
            return testUser.Post("http://localhost:8080/follower",user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
