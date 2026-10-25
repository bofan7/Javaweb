package com.itwu.controller;

import com.itwu.pojo.Result;
import com.itwu.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 文件上传控制器
 */
@Slf4j
@RestController
public class UploadController {

    /**
     *本地磁盘存储方案
     *

    @PostMapping("/upload")
    public Result upload(String name, Integer age, MultipartFile file) throws IOException {
        log.info("接收参数：{},{},{}", name, age, file);

        // 获取原始文件名
        String originalFilename = file.getOriginalFilename();

        // 保存文件C:/Users/32770/Desktop
        file.transferTo(new File("C:/Users/32770/Desktop/images/" + originalFilename));

        return Result.success();
    }
    */
@Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        log.info("接收参数：{}", file.getOriginalFilename());
        //将文件交给oss存储管理
        String url=aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());
        return Result.success(url);
    }



}