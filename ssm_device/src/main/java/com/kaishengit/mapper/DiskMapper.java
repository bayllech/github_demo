package com.kaishengit.mapper;

import com.kaishengit.pojo.Disk;

import java.io.InputStream;
import java.util.List;

public interface DiskMapper {

    List<Disk> findByFid(Integer fid);

    void saveFolder(Disk disk);

    void saveFile(Disk disk);

    InputStream findById(Integer id);

    Disk findDiskById(Integer id);
}
