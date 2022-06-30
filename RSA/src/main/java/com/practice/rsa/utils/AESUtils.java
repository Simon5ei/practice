package com.practice.rsa.utils;

import cn.hutool.crypto.symmetric.AES;

/**
 * DES加密助手
 */
public class AESUtils {
    public static AES aes = new AES("CBC", "PKCS7Padding", "0123456789ABHAEQ".getBytes(),"DYgjCEIMVrj2W9xN".getBytes());
    // 加密为16进制表示
    public static String AESEncrypt(String content){
        return aes.encryptHex(content);
    }
    // 解密
    public static String AESDecrypt(String encryptHex){
        return aes.decryptStr(encryptHex);
    }

}
