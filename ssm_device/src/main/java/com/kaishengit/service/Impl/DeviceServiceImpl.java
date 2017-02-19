package com.kaishengit.service.Impl;

import com.google.common.collect.Lists;
import com.kaishengit.dto.DeviceRentDto;
import com.kaishengit.mapper.DeviceMapper;
import com.kaishengit.mapper.DeviceRentDetailMapper;
import com.kaishengit.mapper.DeviceRentDocMapper;
import com.kaishengit.mapper.RentMapper;
import com.kaishengit.pojo.Device;
import com.kaishengit.pojo.DeviceRent;
import com.kaishengit.pojo.DeviceRentDetail;
import com.kaishengit.pojo.DeviceRentDoc;
import com.kaishengit.service.DeviceService;
import com.kaishengit.util.SerialNumberUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {
    private Logger logger = LoggerFactory.getLogger(DeviceServiceImpl.class);

    @Autowired
    private DeviceMapper deviceMapper;

    @Autowired
    private RentMapper rentMapper;

    @Autowired
    private DeviceRentDetailMapper detailMapper;

    @Autowired
    private DeviceRentDocMapper docMapper;

    @Override
    public List<Device> findAllDevice() {
        return deviceMapper.findAllDevice();
    }

    @Override
    public Device findDeviceById(Integer id) {
        return deviceMapper.findDeviceById(id);
    }

    @Override
    public String saveRent(DeviceRentDto deviceRentDto) {
        //保存公司合同
        DeviceRent rent = new DeviceRent();
        rent.setAddress(deviceRentDto.getAddress());
        rent.setBackDate(deviceRentDto.getBackDate());
        rent.setCardNum(deviceRentDto.getCardNum());
        rent.setCompanyName(deviceRentDto.getCompanyName());
        //todo 完善系统登录功能
        rent.setCreateUser("admin");
        rent.setFax(deviceRentDto.getFax());
        rent.setLastCost(deviceRentDto.getLastCost());
        rent.setTel(deviceRentDto.getTel());
        rent.setLinkMan(deviceRentDto.getLinkMan());
        rent.setPreCost(deviceRentDto.getPreCost());
        rent.setTotalPrice(deviceRentDto.getTotalPrice());
        rent.setRentDate(deviceRentDto.getRentDate());
        rent.setTotalDay(deviceRentDto.getTotalDate());
        rent.setSerialNum(SerialNumberUtil.getSerialNumber());

        rentMapper.saveRent(rent);

        //保存合同详情
        List<DeviceRentDto.DeviceArrayBean> deviceArray = deviceRentDto.getDeviceArray();
        List<DeviceRentDetail> detailList = Lists.newArrayList();

        for (DeviceRentDto.DeviceArrayBean bean : deviceArray) {
            DeviceRentDetail deviceRentDetail = new DeviceRentDetail();
            deviceRentDetail.setDeviceName(bean.getName());
            deviceRentDetail.setDevicePrice(bean.getPrice());
            deviceRentDetail.setDeviceUnit(bean.getUnit());
            deviceRentDetail.setNum(bean.getNum());
            deviceRentDetail.setTotalPrice(bean.getPrice());
            deviceRentDetail.setRentId(rent.getId());

            detailList.add(deviceRentDetail);

        }
        if (!detailList.isEmpty()) {
            detailMapper.saveDetail(detailList);
        }

        //保存文档
        List<DeviceRentDto.DocBean> fileArray = deviceRentDto.getFileArray();

        List<DeviceRentDoc> docList = Lists.newArrayList();
        for (DeviceRentDto.DocBean bean: fileArray){
            DeviceRentDoc doc = new DeviceRentDoc();
            doc.setSourceName(bean.getSourceFileName());
            doc.setNewName(bean.getNewFileName());
            doc.setRentId(rent.getId());

            docList.add(doc);
        }
        if (!docList.isEmpty()) {
            docMapper.saveDoc(docList);
        }

        //返回财务流水
        return rent.getSerialNum();
    }

    @Override
    public DeviceRent findRentBySerialNum(String serialNumber) {
        return deviceMapper.findRentBySerialNum(serialNumber);
    }

    @Override
    public List<DeviceRentDetail> findRentDetailByRentId(Integer id) {
        return detailMapper.findRentDetailByRentId(id);
    }

    @Override
    public List<DeviceRentDoc> findDocByRentId(Integer id) {
        return docMapper.findDocByRentId(id);
    }

    @Override
    public List<DeviceRent> findAllRent() {
        return deviceMapper.findAllRent();
    }

    @Override
    public void saveDevice(Device device) {
        deviceMapper.saveDevice(device);
    }

}
