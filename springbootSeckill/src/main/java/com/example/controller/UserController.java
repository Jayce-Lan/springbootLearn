package com.example.controller;

import com.example.controller.viewobject.UserVO;
import com.example.error.BusinessException;
import com.example.error.EmBusinessError;
import com.example.response.CommonReturnType;
import com.example.service.UserService;
import com.example.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @RequestMapping("/get")
    @ResponseBody
    public CommonReturnType getUser(@RequestParam(name = "id") Integer id) throws BusinessException {
        UserModel userModel = userService.getUserById(id);
        //若用户不存在
        if (userModel == null)
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);

        UserVO userVO = convertFromModel(userModel);

        return CommonReturnType.create(userVO);
    }

    /**
     * 将原本开发使用的一些对象信息封装成为一个前端对象
     *
     * @param userModel 传入后端的一个业务处理对象
     * @return 返回可以展示给前端的对象
     */
    private UserVO convertFromModel(UserModel userModel) {
        if (userModel == null)
            return null;
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userModel, userVO);
        return userVO;
    }
}
