package com.practice.rsa.utils;

import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.DES;

public class DESUtils {
    public static DES des = new DES(Mode.CTS, Padding.PKCS5Padding, "0CoJUm6Qyw8W8jud".getBytes(), "01020304".getBytes());
    public static String DESEncrypt(String content){
        return des.encryptHex(content);
    }
    // 解密
    public static String DESDecrypt(String encryptHex){
        return des.decryptStr(encryptHex);
    }
}
