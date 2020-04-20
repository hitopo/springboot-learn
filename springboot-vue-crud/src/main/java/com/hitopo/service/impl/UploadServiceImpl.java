package com.hitopo.service.impl;

import com.hitopo.common.R;
import com.hitopo.common.ResultEnum;
import com.hitopo.exception.CustomizedException;
import com.hitopo.service.UploadService;
import com.hitopo.util.FileUploadUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hitopo
 * @version v1.0
 * @classname UploadServiceImpl
 * @time 2020/4/20 16:30
 * @description 文件上传服务实现类
 */
@Service
public class UploadServiceImpl implements UploadService {

    @Override
    public R upload(MultipartFile picFile, String urlPrefix) {
        // 为了放置文件名重复，重新设置文件名
        String originalFileName = picFile.getOriginalFilename();
        // 文件类型
        String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        // 生成新的文件名
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String fileName = dateTime.format(formatter) + "." + type;
        String uploadPath;
        try {
            // 上传文件
            uploadPath = FileUploadUtil.upload(picFile.getInputStream(), "images/", fileName);
        } catch (IOException e) {
            throw new CustomizedException(ResultEnum.FILE_UPLOAD_ERROR);
        }
        // uploadPath:/upload/image/test.jpg
        // 组装访问路径
        String url = urlPrefix + uploadPath;
        Map<String, Object> map = new HashMap<>();
        map.put("url", url);
        return R.create(ResultEnum.CREATED, map);
    }
}
