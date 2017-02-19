package com.kaishengit.service;

import com.kaishengit.dto.DeviceRentDto;
import com.kaishengit.pojo.Device;
import com.kaishengit.pojo.DeviceRent;
import com.kaishengit.pojo.DeviceRentDetail;
import com.kaishengit.pojo.DeviceRentDoc;

import java.util.List;

/**
 * Created by bayllech on 2017/2/16.
 */
public interface DeviceService {

    List<Device> findAllDevice();

    Device findDeviceById(Integer id);

    String saveRent(DeviceRentDto deviceRentDto);

    DeviceRent findRentBySerialNum(String serialNumber);

    List<DeviceRentDetail> findRentDetailByRentId(Integer id);

    List<DeviceRentDoc> findDocByRentId(Integer id);

    List<DeviceRent> findAllRent();

    void saveDevice(Device device);
}
