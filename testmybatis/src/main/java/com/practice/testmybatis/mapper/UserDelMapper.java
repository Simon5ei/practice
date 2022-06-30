package com.practice.testmybatis.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDelMapper {
    public int delByIDMapper(Integer userID);
}
