/**
 * @author Wiretender
 * @version 1.0
 */
package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.util.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {


    /**
     * 这里使用的是本地上传的接口，未配置教程案例中的阿里云OSS接口
     * @param name
     * @param age
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    public Result upload(String name, Integer age, MultipartFile file) throws IOException {
        log.info("接收参数： {} {} {}", name, age, file);
        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFileName = UUID.randomUUID().toString() + extension;
        file.transferTo(new File("E:/BaiduNetdiskDownload/images/" + originalFilename));
        return Result.success();
    }


    /**
     * 下述代码为阿里云OSS实现
     */
//    @Autowired
//    private AliyunOSSOperator aliyunOSSOperator;
//
//    @PostMapping("/upload")
//    public Result upload(MultipartFile file) throws Exception {
//        log.info("文件上传: {}", file.getOriginalFilename());
//
//        //将文件交给OSS存储管理
//        String url = aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());
//        log.info("文件上传OSS, url: {}", url);
//
//        return Result.success(url);
//    }
}
