package com.kaishengit.mapper;

import com.kaishengit.pojo.DeviceRentDetail;
import com.kaishengit.pojo.WorkTypeDetail;

import java.util.List;

public interface DeviceRentDetailMapper {
    void saveDetail(List<DeviceRentDetail> detailList);

    List<DeviceRentDetail> findRentDetailByRentId(Integer id);

}
