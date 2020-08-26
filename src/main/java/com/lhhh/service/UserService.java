package com.lhhh.service;

import com.lhhh.bean.User;

/**
 * @author: lhhh
 * @date: Created in 2020/7/29
 * @description:
 * @version:1.0
 */
public interface UserService {
    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
     User login(String username, String password);
}
