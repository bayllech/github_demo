package com.kaishengit.service;

import com.kaishengit.pojo.Device;

import java.util.List;

/**
 * Created by bayllech on 2017/2/16.
 */
public interface DeviceService {

    List<Device> findAllDevice();

    Device findDeviceById(Integer id);
}
