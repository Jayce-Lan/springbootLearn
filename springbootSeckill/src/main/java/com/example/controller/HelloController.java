package com.example.controller;

import com.example.dao.UserDOMapper;
import com.example.dataobject.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    UserDOMapper userDOMapper;

    @RequestMapping("/")
    public String home() {
        UserDO userDO = userDOMapper.selectByPrimaryKey(1);
        if (userDO == null) {
            return "用户不存在";
        } else {
            return userDO.toString();
        }
    }
}
