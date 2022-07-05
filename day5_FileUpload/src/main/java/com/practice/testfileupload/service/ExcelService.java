package com.practice.testfileupload.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.practice.testfileupload.model.excel;
import com.practice.testfileupload.model.file;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//Run application with VM option : --add-opens java.base/java.lang=ALL-UNNAMED

@Service
public class ExcelService {
    @Autowired
    fileService fileService;
    public void export(HttpServletResponse response) throws IOException {
        List<file> tab = fileService.getTab();

        // 定义excel的页签
        List<excel> excels = new ArrayList<>();

        // 字节数组输出流
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // 构建写入对象
        ExcelWriter excelWriter = EasyExcel.write(outputStream).build();

        // 这里可以多个WriteSheet对象
        WriteSheet uploadfile = EasyExcel.writerSheet(0, "file").head(excel.class).build();

        // 这里可以写多个write
        excelWriter.write(tab, uploadfile);

        // 写入完成
        excelWriter.finish();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(new Date());
        String fileName = format + "file.xlsx";

        try {
            outputStream.writeTo(response.getOutputStream());
            response.setContentType("application/octet-stream");
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"; filename*=utf-8''%s", fileName, fileName));
            response.setContentLengthLong(outputStream.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
