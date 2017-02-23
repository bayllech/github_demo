package com.kaishengit.mapper;

import com.kaishengit.dto.DeviceRentDto;
import com.kaishengit.pojo.Device;
import com.kaishengit.pojo.DeviceRent;

import java.util.List;
import java.util.Map;

public interface DeviceMapper {

    List<Device> findAllDevice();

    Device findDeviceById(Integer id);

    DeviceRent findRentBySerialNum(String serialNumber);

    List<DeviceRent> findAllRent();

    void saveDevice(Device device);

    DeviceRent findRentById(Integer id);

    void updateCurrentNum(Device device);

    List<DeviceRent> findRentByQueryParam(Map<String, Object> queryParam);

    Long countDeviceRent();

    Long filterCountRent(Map<String, Object> queryParam);

    void updateRentState(DeviceRent rent);

    Device findDeviceByName(String deviceName);

    void updateDeviceCurrent(Device device);
}
