package com.sky.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CommonService {
    /**
     * 上传文件
     * @param file
     * @return
     */
    String upload(MultipartFile file) throws Exception;
}
