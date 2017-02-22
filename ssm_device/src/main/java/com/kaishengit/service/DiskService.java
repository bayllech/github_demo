package com.kaishengit.service;


import com.kaishengit.pojo.Disk;

import java.util.List;

public interface DiskService {

    List<Disk> findByFid(Integer path);

    void saveFolder(Disk disk);
}
