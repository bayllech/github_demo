package com.kaishengit.service.Impl;

import com.google.common.collect.Lists;
import com.kaishengit.exception.NotFoundException;
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
import org.springframework.transaction.annotation.Transactional;
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

    @Override
    public InputStream findById(Integer id) {
        Disk disk = findDiskById(id);
        if (disk == null) {
            return null;
        } else {
            File file = new File(savePath + "/" + disk.getName());
            // File file = new File(new File(savePath), disk.getName());
            // file = new File(savePath, disk.getName());
            try {
                return new FileInputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    @Override
    public Disk findDiskById(Integer id) {
        return diskMapper.findDiskById(id);
    }

    @Override
    @Transactional
    public void delById(Integer id) {
        Disk disk = findDiskById(id);
        if (disk == null) {
            throw new NotFoundException();
        } else {
            if (disk.getType().equals(Disk.FILE_TYPE)) {
                //删除文件
                File file = new File(savePath, disk.getName());
                file.delete();
                //删除数据库记录
                diskMapper.delFileById(id);
            } else {
                //删除文件夹
                List<Disk> diskList = diskMapper.findAll();
                List<Integer> delIdList = Lists.newArrayList();
                findDelId(diskList, delIdList, id);
                delIdList.add(id);

                //批量删除数据库记录
                diskMapper.batchDel(delIdList);
            }
        }
    }

    private void findDelId(List<Disk> diskList, List<Integer> delIdList, Integer id) {
        for (Disk disk:diskList) {
            if (disk.getFid().equals(id)) {
                delIdList.add(disk.getId());
                if (disk.getType().equals(Disk.DIRECTRY_TYPE)) {
                    findDelId(diskList,delIdList,disk.getId());
                } else {
                    File file = new File(savePath, disk.getName());
                    file.delete();
                }
            }
        }
    }


}
