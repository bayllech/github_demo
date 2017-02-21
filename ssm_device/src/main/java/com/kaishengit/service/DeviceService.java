package com.kaishengit.service;

import com.kaishengit.dto.DeviceRentDto;
import com.kaishengit.pojo.Device;
import com.kaishengit.pojo.DeviceRent;
import com.kaishengit.pojo.DeviceRentDetail;
import com.kaishengit.pojo.DeviceRentDoc;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

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

    InputStream loadDocById(Integer id) throws FileNotFoundException;

    DeviceRentDoc findDocById(Integer id);

    DeviceRent findRentById(Integer id);

    void loadZipDocs(DeviceRent rent,ZipOutputStream zipOutputStream) throws IOException;

    List<DeviceRent> findRentByQueryParam(Map<String, Object> queryParam);

    Long countDeviceRent();

    Long filterCountRent(Map<String, Object> queryParam);
}
