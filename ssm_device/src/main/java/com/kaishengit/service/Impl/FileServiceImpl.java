package com.kaishengit.service.Impl;

import com.kaishengit.service.FileService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Value("${uploadPath}")
    private String uploadPath;

    @Override
    public String uploadFile(String originalFilename, String contentType, InputStream inputStream) {
        String randomFileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
        File file = new File(new File(uploadPath), randomFileName);

        try {
            OutputStream outputStream = new FileOutputStream(file);
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
            outputStream.close();
            inputStream.close();
            return randomFileName;
        } catch (IOException e) {
            logger.error("文件上传异常", e);
            throw new RuntimeException("文件上传失败", e);
        }
    }
}
