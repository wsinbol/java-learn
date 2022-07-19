package com.example.demo.server;

import com.example.demo.bean.User;
import com.example.demo.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServer {

    @Autowired
    private UserDao userDao;

    public User selectUserByName(String name){
        return userDao.findByUserName(name);
    }


    public int insertUser(User user){
        return userDao.insertUser(user);
    }
}
