package com.practice.testfileupload.service;


import com.practice.testfileupload.model.file;

import java.util.List;

public interface fileService {
    Boolean Store(String name,String school,String telephone, String file_path);

    List<file> getTab();

    String getAdd(String name);
}
