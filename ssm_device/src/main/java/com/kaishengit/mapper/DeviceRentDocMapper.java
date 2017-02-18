package com.kaishengit.mapper;

import com.kaishengit.pojo.DeviceRentDoc;

import java.util.List;

public interface DeviceRentDocMapper {
    void saveDoc(List<DeviceRentDoc> docList);

    List<DeviceRentDoc> findDocByRentId(Integer rentId);
}
