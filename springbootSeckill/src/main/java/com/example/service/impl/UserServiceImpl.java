package com.example.service.impl;

import com.example.dao.UserDOMapper;
import com.example.dao.UserPasswordDOMapper;
import com.example.dataobject.UserDO;
import com.example.dataobject.UserPasswordDO;
import com.example.service.UserService;
import com.example.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDOMapper userDOMapper;
    @Autowired
    UserPasswordDOMapper userPasswordDOMapper;

    @Override
    public UserModel getUserById(Integer id) {
        //调用userdomapper获取到对应的用户dataobject
        UserDO userDO = userDOMapper.selectByPrimaryKey(id);
        if (userDO == null) {
            return null;
        }
        //通过用户id获取对应的用户加密密码信息
        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(id);

        return converFromDataObject(userDO, userPasswordDO);
    }

    /**
     * 将持久层的对象整合，组装成UserModel对象
     *
     * @param userDO
     * @param userPasswordDO
     * @return
     */
    private UserModel converFromDataObject(UserDO userDO, UserPasswordDO userPasswordDO) {
        if (userDO == null) {
            return null;
        }

        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDO, userModel);    //复制属性

        if (userPasswordDO != null) {
            userModel.setEncrptPassword(userPasswordDO.getEncrptPassword());
        }

        return userModel;
    }
}
