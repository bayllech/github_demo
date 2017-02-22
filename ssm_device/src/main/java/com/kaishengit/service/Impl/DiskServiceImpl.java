package com.kaishengit.service.Impl;

import com.kaishengit.exception.ServiceException.ServiceException;
import com.kaishengit.mapper.DiskMapper;
import com.kaishengit.pojo.Disk;
import com.kaishengit.service.DiskService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.print.attribute.standard.Fidelity;
import java.io.*;
import java.util.List;
import java.util.UUID;

@Service
public class DiskServiceImpl implements DiskService{

    @Autowired
    private DiskMapper diskMapper;

    @Value("${uploadPath}")
    private String savePath;

    @Override
    public List<Disk> findByFid(Integer fid) {
        return diskMapper.findByFid(fid);
    }

    @Override
    public void saveFolder(Disk disk) {
        //todo完善用户模块
        disk.setCreateUser("admin");
        disk.setType(Disk.DIRECTRY_TYPE);
        diskMapper.saveFolder(disk);
    }

    @Override
    public void saveFile(Integer fid, MultipartFile file) {
        //保存文件到磁盘
        String sourceName = file.getOriginalFilename();
        Long size = file.getSize();

        String newName = UUID.randomUUID().toString();
        if (sourceName.lastIndexOf(".") != -1) {
            newName += sourceName.substring(sourceName.lastIndexOf("."));
        }

        try {
            File saveFile = new File(savePath, newName);
            FileOutputStream outputStream = new FileOutputStream(saveFile);
            InputStream inputStream = file.getInputStream();
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (IOException ex) {
            throw new ServiceException("文件保存到磁盘异常",ex);
        }

        //保存文件到数据库
        Disk disk = new Disk();
        disk.setType(Disk.FILE_TYPE);
        disk.setSourceName(sourceName);
        disk.setCreateUser("admin");
        disk.setFid(fid);
        disk.setName(newName);
        disk.setSize(FileUtils.byteCountToDisplaySize(size));

        diskMapper.saveFile(disk);

    }
}
