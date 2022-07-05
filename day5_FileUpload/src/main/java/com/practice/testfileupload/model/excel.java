package com.practice.testfileupload.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class excel {
    @ExcelProperty(value = {"用户名"}, index = 0)
    private String name;

    @ExcelProperty(value = {"学校"}, index = 1)
    private String school;

    @ExcelProperty(value = {"手机号"}, index = 2)
    private String telephone;

    @ExcelProperty(value = {"文件地址"}, index = 3)
    private String file_path;

}
