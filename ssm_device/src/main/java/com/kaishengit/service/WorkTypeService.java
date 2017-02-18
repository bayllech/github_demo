package com.kaishengit.service;

import com.kaishengit.dto.DeviceRentDto;
import com.kaishengit.pojo.WorkType;

import java.util.List;

public interface WorkTypeService {

    WorkType findDeviceById(Integer id);

    List<WorkType> findAllWorkType();

    String saveRent(DeviceRentDto deviceRentDto);
}
