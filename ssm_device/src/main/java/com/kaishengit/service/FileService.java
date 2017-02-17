package com.kaishengit.service;

import org.springframework.stereotype.Service;

import java.io.InputStream;

public interface FileService {

    String uploadFile(String originalFilename, String contentType, InputStream inputStream);
}
