package com.practice.testmybatis.mapper;

import com.practice.testmybatis.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserNameMapper {
    public User queryNameMapper(String name);
}
