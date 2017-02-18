package com.kaishengit.mapper;

import com.kaishengit.dto.DeviceRentDto;
import com.kaishengit.pojo.Device;
import com.kaishengit.pojo.DeviceRent;

import java.util.List;

public interface DeviceMapper {

    List<Device> findAllDevice();

    Device findDeviceById(Integer id);

    DeviceRent findRentBySerialNum(String serialNumber);
}