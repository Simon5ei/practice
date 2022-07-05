package com.practice.testmybatis.controller;

import com.practice.testmybatis.auth.PassToken;
import com.practice.testmybatis.domain.User;
import com.practice.testmybatis.mapper.UserAddMapper;
import com.practice.testmybatis.mapper.UserDelMapper;
import com.practice.testmybatis.mapper.UserUpdateMapper;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserDelMapper userDelMapper;

    @Autowired
    private UserUpdateMapper userUpdateByIDMapper;

    @Autowired
    private UserAddMapper userAddMapper;

    @RequestMapping("/del/{userID}")
    @ResponseBody
    public int ShowDelRes(@PathVariable("userID") Integer userID) {
        return userDelMapper.delByIDMapper(userID);
    }

    @RequestMapping("/update/{userID}")
    @ResponseBody
    public String ShowDelRes(@Param("name") String name, @Param("pswd") String pswd, @PathVariable(name = "userID") Integer userID) {
        User u = new User();
        u.setIduser(userID);
        u.setName(name);
        u.setPswd(pswd);
        Integer res;
        try {
            res = userUpdateByIDMapper.updateByIDMapper(u);
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException) {
                return "DuplicateKey!";
            } else {
                return "Strange?!!";
            }
        }
        return res.toString();
    }

    @RequestMapping("/add")
    @ResponseBody
    public String ShowAddRes(@RequestParam("name") String name, @RequestParam("pswd") String pswd) {
        logger.info(name+","+pswd);
        User u = new User();
        u.setName(name);
        u.setPswd(pswd);
        u.setIsdel(false);
        Integer res;
        try {
            res = userAddMapper.addUserMapper(u);
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException) {
                return "DuplicateKey!";
            } else {
                return "Strange?!!";
            }
        }
        return res.toString();
    }
    @PassToken
    @RequestMapping("/addp")
    @ResponseBody
    public String ShowAddResPF(@RequestParam("name") String name, @RequestParam("pswd") String pswd) {
        User u = new User();
        u.setName(name);
        u.setPswd(pswd);
        u.setIsdel(false);
        Integer res;
        try {
            res = userAddMapper.addUserMapper(u);
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException) {
                return "DuplicateKey!";
            } else {
                return "Strange?!!";
            }
        }
        return res.toString();
    }
}
