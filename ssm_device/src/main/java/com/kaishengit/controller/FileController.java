package com.kaishengit.controller;

import com.google.common.collect.Maps;
import com.kaishengit.dto.AjaxResult;
import com.kaishengit.service.FileService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

  /*  @PostMapping("/upload")
    public void ajaxUploadFile(@RequestParam("files") MultipartFile[] files, HttpServletRequest request) {
        for (MultipartFile file : files) {
            try {
                String fileName = UUID.randomUUID().toString() + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
                System.out.println("文件名： " + fileName);
                byte[] bytes = file.getBytes();
                System.out.println("file.getBytes()： " );
                for (int i = 0; i<10;i++) {
                    System.out.print(bytes[i]);
                }
                System.out.println("");
                *//*byte[] bytes1 = FileUtils.readFileToByteArray((File) file);
                System.out.println("FileUtils: " + bytes1.toString());*//*
                byte[] bytes2 = IOUtils.toByteArray(file.getInputStream());
                System.out.println("IOUtils: " );
                for (int i = 0; i<10;i++) {
                    System.out.print(bytes2[i]);
                }
                System.out.println("------------------");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }*/


    @PostMapping("/upload")
    @ResponseBody
    public AjaxResult ajaxUploadFile(MultipartFile file) {
        try {
            String fileName = fileService.uploadFile(file.getOriginalFilename(), file.getContentType(), file.getInputStream());
            Map<String, String> map = Maps.newHashMap();
            map.put("newFileName", fileName);
            map.put("sourceFileName", file.getOriginalFilename());
            return new AjaxResult(map);

        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(AjaxResult.ERROR, e.getMessage());
        }
    }
}
