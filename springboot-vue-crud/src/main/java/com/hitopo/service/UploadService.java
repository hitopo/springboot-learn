package com.hitopo.service;

import com.hitopo.common.R;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author hitopo
 * @version v1.0
 * @classname UploadService
 * @time 2020/4/20 16:30
 * @description 文件上传服务类
 */
public interface UploadService {
    /**
     * 文件上传
     * @param picFile 图片文件
     * @param
     */
    R upload(MultipartFile picFile,String urlPrefix);
}
