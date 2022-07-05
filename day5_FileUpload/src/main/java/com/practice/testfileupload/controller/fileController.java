package com.practice.testfileupload.controller;

import com.practice.testfileupload.annotations.FileCheck;
import com.practice.testfileupload.enmus.FileType;
import com.practice.testfileupload.service.ExcelService;
import com.practice.testfileupload.service.fileServiceImpl;
import com.practice.testfileupload.utils.DESUtils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

@Controller
public class fileController {
    List<String> supportFileFormats =new ArrayList<>(Arrays.asList("png".split(",")));
    private boolean checkFormats(String fileFullName){
        String suffix = fileFullName.substring(fileFullName.lastIndexOf(".") + 1).toLowerCase();
        return supportFileFormats.stream().anyMatch(suffix::contains);
    }
    private static final Logger log = LoggerFactory.getLogger(fileController.class);
    @Autowired
    private fileServiceImpl fileService;
    @Autowired
    private ExcelService excelService;
    //访问http://localhost//upload_pre 显示upload.html页面
    @GetMapping("/upload_pre")
    public String uploadPre() { // 通过model可以实现内容的传递
        return "upload";
    }

    //upload
    @PostMapping("/upload")
    @ResponseBody
    @FileCheck(supportedFileTypes = {FileType.PNG}, type = FileCheck.CheckType.MAGIC_NUMBER)
    public Object upload(String name,String school,String telephone, MultipartFile resume) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        if (resume != null) {    // 现在有文件上传
            String originalFilename = resume.getOriginalFilename();
            log.info(originalFilename);
            assert originalFilename != null;
            boolean checkFormats = checkFormats(originalFilename);
//            if (!checkFormats){
//                return "wrong file_type";
//            }
            //姓名
            map.put("name-param", name);
            map.put("school-param", school);
            map.put("telephone-param", telephone);

            //文件名
            map.put("resume-name", resume.getName());
            map.put("content-type", resume.getContentType());
            //文件大小
            map.put("resume-size", resume.getSize());
            String fileName = UUID.randomUUID() + "."
                    + resume.getContentType().substring(resume.getContentType().lastIndexOf("/") + 1);    // 创建文件名称
            //文件路径  位置 + 文件名
            String filePath = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                    .getRequest().getServletContext().getRealPath("/") + fileName;
            //文件路径
            map.put("resume-path", filePath);
            File saveFile = new File(filePath);
            resume.transferTo(saveFile);        // 文件保存
            fileService.Store(name,school,DESUtils.DESEncrypt(telephone),filePath);
            return map;
        } else {
            return "nothing";
        }
    }

    @GetMapping("/gettab")
    @ResponseBody
    public String export(HttpServletResponse response) throws IOException {
        excelService.export(response);
        return "success";
    }

    @RequestMapping("/download")
    public String download(@Param("name") String name, HttpServletResponse response) {
        try {
            // path是指想要下载的文件的路径
            File file = new File(fileService.getAdd(name));
            log.info(file.getPath());
            // 获取文件名
            String filename = file.getName();
            // 获取文件后缀名
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
            log.info("文件后缀名：" + ext);

            // 将文件写入输入流
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStream fis = new BufferedInputStream(fileInputStream);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();

            // 清空response
            response.reset();
            // 设置response的Header
            response.setCharacterEncoding("UTF-8");
            //Content-Disposition的作用：告知浏览器以何种方式显示响应返回的文件，用浏览器打开还是以附件的形式下载到本地保存
            //attachment表示以附件方式下载   inline表示在线打开   "Content-Disposition: inline; filename=文件名.mp3"
            // filename表示文件的默认名称，因为网络传输只支持URL编码的相关支付，因此需要将文件名URL编码后进行传输,前端收到后需要反编码才能获取到真正的名称
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
            // 告知浏览器文件的大小
            response.addHeader("Content-Length", "" + file.length());
            OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            outputStream.write(buffer);
            outputStream.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "Success";
    }
}



