package com.practice.testmybatis.mapper;

import com.practice.testmybatis.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserUpdateMapper {
    public int updateByIDMapper(User u);
}
