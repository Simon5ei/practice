package com.practice.testfileupload.service;

import com.practice.testfileupload.dao.fileDao;
import com.practice.testfileupload.model.file;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class fileServiceImpl implements fileService {

    @Autowired
    fileDao fileDao;
    @Override
    public Boolean Store(@Param("name") String name,@Param("school") String school,@Param("telephone") String telephone,@Param("file_path") String file_path){
        return fileDao.insert(name, school, telephone, file_path);
    }

    @Override
    public List<file> getTab(){
        return fileDao.getTab();
    }

    @Override
    public String getAdd(@Param("name") String name){
        return fileDao.getAdd(name);
    }
}
