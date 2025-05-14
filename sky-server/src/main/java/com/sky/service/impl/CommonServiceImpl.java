package com.sky.service.impl;

import com.sky.service.CommonService;
import com.sky.utils.AliyunOSSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 通用接口业务层
 */
@Service
public class CommonServiceImpl implements CommonService {
    @Autowired
    private AliyunOSSUtil aliyunOSSUtil;
    /**
     * 上传文件
     * @param file
     * @return
     */
    @Override
    public String upload(MultipartFile file) throws Exception {
        String url = aliyunOSSUtil.upload(file.getBytes(), file.getOriginalFilename());
        return url;
    }
}
