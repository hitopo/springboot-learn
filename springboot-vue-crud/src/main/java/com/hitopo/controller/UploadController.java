package com.hitopo.controller;

import com.hitopo.common.R;
import com.hitopo.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author hitopo
 * @version v1.0
 * @classname UploadController
 * @time 2020/4/20 16:25
 * @description 文件上传控制器
 */
@RestController
public class UploadController {

    @Autowired
    private UploadService uploadService;

    /**
     * 上传文件
     */
    @PostMapping("/upload")
    public R upload(@RequestParam("picture") MultipartFile picFile) {
        String urlPrefix = "http://127.0.0.1:8080/simple-shop";
        return uploadService.upload(picFile, urlPrefix);
    }
}

