package com.practice.rsa.dao;

import com.practice.rsa.model.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Resource;

@Mapper
@Resource
public interface AdminDao {
    Admin selectAdmin(@Param("username") String username);
    String selectPassword(@Param("username") String username);
    String selectUsername(@Param("username") String username);
    String selectRelName(@Param("username") String username);
    // 用户注册时需要
    void add(@Param("rel_name") String rel_name,@Param("username") String username,@Param("password") String password);
}
