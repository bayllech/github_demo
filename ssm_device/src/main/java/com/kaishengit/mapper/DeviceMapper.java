package com.kaishengit.mapper;

import com.kaishengit.dto.DeviceRentDto;
import com.kaishengit.pojo.Device;

import java.util.List;

public interface DeviceMapper {

    List<Device> findAllDevice();

    Device findDeviceById(Integer id);

    void saveRent(DeviceRentDto deviceRentDto);
}
