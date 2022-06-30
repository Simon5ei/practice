package com.practice.rsa.service;

import com.practice.rsa.dao.AdminDao;
import com.practice.rsa.model.Admin;
import com.practice.rsa.utils.AESUtils;
import com.practice.rsa.utils.DESUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminDao adminDao;

    @Override
    public Admin login(String username, String password) {
        return adminDao.selectAdmin(AESUtils.AESEncrypt(username));
    }

    @Override
    public String selectPassword(String username) {
        return adminDao.selectPassword(AESUtils.AESEncrypt(username));
    }
    @Override
    public String selectRelName(String username) {
        return adminDao.selectRelName(AESUtils.AESEncrypt(username));
    }
    @Override
    public String selectUsername(String username) {
        return adminDao.selectUsername(AESUtils.AESEncrypt(username));
    }
    @Override
    public void add(String rel_name,String username,String password) {
        adminDao.add(DESUtils.DESEncrypt(rel_name), AESUtils.AESEncrypt(username), password);
    }
}
