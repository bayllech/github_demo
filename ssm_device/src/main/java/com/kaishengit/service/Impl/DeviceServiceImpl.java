package com.kaishengit.service.Impl;

import com.kaishengit.dto.DeviceRentDto;
import com.kaishengit.mapper.DeviceMapper;
import com.kaishengit.pojo.Device;
import com.kaishengit.service.DeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {
    private Logger logger = LoggerFactory.getLogger(DeviceServiceImpl.class);

    @Autowired
    private DeviceMapper deviceMapper;


    @Override
    public List<Device> findAllDevice() {
        return deviceMapper.findAllDevice();
    }

    @Override
    public Device findDeviceById(Integer id) {
        return deviceMapper.findDeviceById(id);
    }

    @Override
    public void saveRent(DeviceRentDto deviceRentDto) {
        deviceMapper.saveRent(deviceRentDto);
    }

}
