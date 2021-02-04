package com.example.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(IOException.class)
    public ModelAndView uploadException(IOException e) throws IOException {
        ModelAndView mav = new ModelAndView();
        mav.addObject("msg", "上传文件大小超出限制！");
        mav.setViewName("error");

        return mav;
    }
}
