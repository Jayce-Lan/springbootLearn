package com.example.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalConfig {
    /**
     * ControllerAdvice 注解的全局组件
     *
     * @return 根据userInfo这个key值返回map集合
     */
    @ModelAttribute(value = "userInfo") //这里的value代表着返回数据的key值
    public Map<String, String> userInfo() {
        Map<String, String> map = new HashMap<String, String>();

        map.put("username", "Jim");
        map.put("gender", "男");

        return map;
    }
}
