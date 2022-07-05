package com.practice.testserver.controller;

import com.practice.testserver.model.UserFollowerList;
import com.practice.testserver.model.UserInfo;
import com.practice.testserver.model.UserTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
public class testController {

    private static final Logger logger = LoggerFactory.getLogger(testController.class);
    @GetMapping("/info")
    @ResponseBody
    public List<String> getInfo(@RequestParam("id") Integer id, @RequestParam("name") String name, @RequestParam("followerCount") Integer followerCount, @RequestParam("nikeName") String nikeName){
        logger.info(id.toString()+","+name+","+followerCount.toString()+","+ nikeName);
        return Arrays.asList(id.toString(), name, followerCount.toString(), nikeName);

    }
    @PostMapping("/info")
    @ResponseBody
    public String getInfoPost(@RequestBody UserInfo userInfo){
        logger.info(userInfo.toString());
        return userInfo.toString();
    }
    @PostMapping("/follower")
    @ResponseBody
    public String getFollower(@RequestBody UserFollowerList userFollowerList){
        logger.info(userFollowerList.toString());
        return userFollowerList.toString();
    }
    @PostMapping ("/tag")
    @ResponseBody
    public String getTag(@RequestBody UserTag userTag){
        logger.info(userTag.toString());
        return userTag.toString();
    }
}
