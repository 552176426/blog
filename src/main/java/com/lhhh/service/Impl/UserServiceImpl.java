package com.lhhh.service.Impl;

import com.lhhh.bean.User;
import com.lhhh.dao.UserDao;
import com.lhhh.service.UserService;
import com.lhhh.utils.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: lhhh
 * @date: Created in 2020/7/29
 * @description:
 * @version:1.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public User login(String username, String password) {
        User user = userDao.findUserByUsernameAndPassword(username, Md5Utils.md5(password));
        return user;
    }
}
