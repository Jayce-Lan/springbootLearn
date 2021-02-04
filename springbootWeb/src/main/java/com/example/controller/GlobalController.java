package com.example.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@RestController
public class GlobalController {
    /**
     * 打印所有的全局数据
     *
     * @param model 被定义的全局数据
     * @return 返回全局数据结果
     */
    @GetMapping("/userInfo")
    @ResponseBody
    public String userInfo(Model model) {
        Map<String, Object> map = model.asMap();
        Set<String> keySet = map.keySet();
        Iterator<String> iterator = keySet.iterator();

        String info = "";

        while (iterator.hasNext()) {
            String key = iterator.next();
            Object value = map.get(key);
//            System.out.println(key + ">>>>>>" + value);
            info += "{" + key + ">>>>>>" + value + "}";
        }

        return info;
    }
}
