package com.practice.testfileupload.dao;

import com.practice.testfileupload.model.file;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Resource;
import java.util.List;

@Mapper
@Resource
public interface fileDao {
    Boolean insert(@Param("name") String name, @Param("school") String school, @Param("telephone") String telephone, @Param("file_path") String file_path);
    List<file> getTab();
    String getAdd(@Param("name") String name);
}
