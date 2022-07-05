package com.practice.rsa.controller;
import com.practice.rsa.service.AdminService;
import com.practice.rsa.utils.DESUtils;
import com.practice.rsa.utils.MsgData;
import com.practice.rsa.utils.RSA;
import com.practice.rsa.utils.TestRSA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.security.PrivateKey;

import static com.practice.rsa.utils.TestRSA.getPublicKey;
import static com.practice.rsa.utils.TestRSA.sign;


@Controller
public class AdminController {
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
    RSA rsa = new RSA();

    @Autowired
    AdminService adminService;

    @GetMapping("/")
    public String defaultIndex(HttpSession session) {
        session.setAttribute("publicKey", rsa.publicKey);
        return "index";
    }

    /**
     * 跳转到首页（登录页面）
     *
     * @return .
     */
    @RequestMapping("/user/login")
    @ResponseBody
    public MsgData loginPage(String username,
                             String encrypt_Pwd,String oldPwd) throws Exception {
        logger.info(oldPwd);
        MsgData msgData = new MsgData<>();
        // 后端获取私钥
        String publicKey = rsa.publicKey;
        String privateKey = rsa.privateKey;
        // 获得RSA类型的私钥
        PrivateKey rsaPrivateKey = TestRSA.getPrivateKey(privateKey);
        // 从数据库中获取密文
        String select_Password = adminService.selectPassword(username);
        // 使用私钥解密select_Password
        String Decrypt_database = TestRSA.decrypt(select_Password, rsaPrivateKey);
        // 使用私钥解密经过前端加密用户输入的密文
        String Decrypt_web = TestRSA.decrypt(encrypt_Pwd, rsaPrivateKey);
        // RSA签名
        String sign = TestRSA.sign(oldPwd, TestRSA.getPrivateKey(privateKey));
        // RSA验签
        boolean result = TestRSA.verify(oldPwd, getPublicKey(publicKey), sign);
        logger.info("验签结果:" + result);
        if (Decrypt_database.equals(Decrypt_web)) {
            msgData.setCode(200);
            msgData.setMsg("success");
        }
        else if(result==false){
            msgData.setCode(101);
            msgData.setMsg("verifying fails");
        } else {
            msgData.setCode(101);
            msgData.setMsg("fails");
        }
        return msgData;
    }

    @GetMapping("/go")
    public String loginPage(@RequestParam("username") String username) {
        logger.info(username+","+ DESUtils.DESDecrypt(adminService.selectRelName(username)));
        return "go";
    }

    //进入注册页面
    @GetMapping("/AddPage")
    public String addPage() {
        return "admin/add";
    }

    //注册功能
    @RequestMapping("/doAdd")
    @ResponseBody
    public MsgData add(@RequestParam("rel_name") String rel_name,@RequestParam("username") String username,@RequestParam("password") String password) {
        MsgData msgData = new MsgData<>();
        // 注册：真实姓名和密码可以重复，但是用户名不行。
        String select_username = adminService.selectUsername(username);
        // null：表示未能从数据库中找到与username值一样的数据
        if (select_username == null) {
            adminService.add(rel_name, username, password);
            msgData.setCode(200);
        } else {
            msgData.setCode(101);
        }
        return msgData;
    }
}
