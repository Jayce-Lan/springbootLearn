package com.example.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RestController
public class FileUploadController {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    /**
     * 用于上传单个文件的方法
     * 如果是多文件上传，只需要将参数改为MultipartFile[] uploadFiles，并且遍历该数组即可
     *
     * @param uploadFile 上传的文件
     * @param request 请求参数
     * @return 返回上传结果的字符串
     */
    @PostMapping("/upload")
    public String upload(MultipartFile uploadFile, HttpServletRequest request) {
        String realPath = request.getSession().getServletContext().getRealPath("/uploadFile/");
        String format = sdf.format(new Date());
        File folder = new File(realPath + format);
        if (!folder.isDirectory()) {
            folder.mkdirs();    //创建由此抽象路径名命名的目录，包括任何必需但不存在的父目录
        }

        String oldName = uploadFile.getOriginalFilename();
        String newName = UUID.randomUUID().toString() +
                oldName.substring(oldName.lastIndexOf("."), oldName.length());

        try {
            uploadFile.transferTo(new File(folder, newName));
            //上传成功返回文件路径
            String filePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() +
                    "/uploadFile/" + format + newName;
            return filePath;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "上传失败";
    }

    /**
     * 多文件上传
     *
     * @param uploadFiles
     * @param request
     * @return
     */
    @PostMapping("/uploadfiles")
    public String uploadFiles(MultipartFile[] uploadFiles, HttpServletRequest request) {
        String realPath = request.getSession().getServletContext().getRealPath("/uploadFile/");
        String format = sdf.format(new Date());
        File folder = new File(realPath + format);
        if (!folder.isDirectory()) {
            folder.mkdirs();    //创建由此抽象路径名命名的目录，包括任何必需但不存在的父目录
        }

        try {
            for (int index = 0; index < uploadFiles.length; index++) {
                String oldName = uploadFiles[index].getOriginalFilename();
                String newName = UUID.randomUUID().toString() +
                        oldName.substring(oldName.lastIndexOf("."), oldName.length());
                uploadFiles[index].transferTo(new File(folder, newName));
            }

            String filePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() +
                    "/uploadFile/" + format;
            return filePath;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "上传失败";
    }
}
