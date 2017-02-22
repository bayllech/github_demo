package com.kaishengit.service.Impl;

import com.kaishengit.mapper.DiskMapper;
import com.kaishengit.pojo.Disk;
import com.kaishengit.service.DiskService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.Fidelity;
import java.util.List;

@Service
public class DiskServiceImpl implements DiskService{

    @Autowired
    private DiskMapper diskMapper;

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
}
