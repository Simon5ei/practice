package com.practice.rsa.service;

import com.practice.rsa.model.Admin;

public interface AdminService {
    Admin login(String username, String password);
    String selectPassword(String username);

    String selectRelName(String username);

    String selectUsername(String username);
    void add(String rel_name, String username, String password);
}
