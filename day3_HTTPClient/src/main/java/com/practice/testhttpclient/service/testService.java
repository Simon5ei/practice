package com.practice.testhttpclient.service;

import com.alibaba.fastjson.JSONObject;
import com.practice.testhttpclient.model.User;
import com.practice.testhttpclient.model.UserInfo;
import com.practice.testhttpclient.util.HttpClientUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class testService {
    private static final Logger logger = LoggerFactory.getLogger(testService.class);
    public User BindWithModel(User user){
        User u=new User();
//        logger.info(user.getId().toString());
//        logger.info(user.getName());
//        logger.info(user.getTagAndAgree().toString());
//        logger.info(user.getFollowerList().toString());
//        logger.info(user.getNikeName());
//        logger.info(user.getFollowerCount().toString());
        return u;
    }
    public String Get(String add,User u) throws Exception{
        UserInfo uI=new UserInfo(u);
        return HttpClientUtils.doGet(add+"?"+
                "id="+uI.getId().toString()+"&"+
                "name="+uI.getName()+"&"+
                "followerCount="+uI.getFollowerCount().toString()+"&"+
                "nikeName="+uI.getNikeName()
        );
    }
    public String Post(String add,User u) throws Exception{
        String Json = JSONObject.toJSONString(u);
        return HttpClientUtils.doPostJson(add,Json);
    }
}
