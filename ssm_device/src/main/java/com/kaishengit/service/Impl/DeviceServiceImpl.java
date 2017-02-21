package com.kaishengit.service.Impl;

import com.google.common.collect.Lists;
import com.kaishengit.dto.DeviceRentDto;
import com.kaishengit.exception.ServiceException.ServiceException;
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
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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

    @Value("${uploadPath}")
    private String filePath;

    @Override
    public List<Device> findAllDevice() {
        return deviceMapper.findAllDevice();
    }

    @Override
    public Device findDeviceById(Integer id) {
        return deviceMapper.findDeviceById(id);
    }

    @Override
    @Transactional
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
            //查询库存
            Device device = findDeviceById(bean.getId());
            if (device.getCurrentNum() < bean.getNum()) {
                throw new ServiceException(bean.getName()+" 库存不足");
            } else {
                device.setCurrentNum((int) (device.getCurrentNum() - bean.getNum()));
                deviceMapper.updateCurrentNum(device);
            }

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

    @Override
    public InputStream loadDocById(Integer id){
        DeviceRentDoc doc = docMapper.findDocById(id);
        if (doc == null) {
            return null;
        } else {
            File file = new File(filePath + "/" + doc.getNewName());
            try {
                return new FileInputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    @Override
    public DeviceRentDoc findDocById(Integer id) {
        return docMapper.findDocById(id);
    }

    @Override
    public DeviceRent findRentById(Integer id) {
        return deviceMapper.findRentById(id);
    }

    @Override
    public void loadZipDocs(DeviceRent rent,ZipOutputStream zipOutputStream) throws IOException {
        List<DeviceRentDoc> docList = docMapper.findDocByRentId(rent.getId());
        for (DeviceRentDoc doc : docList) {
            ZipEntry entry = new ZipEntry(doc.getSourceName());
            zipOutputStream.putNextEntry(entry);

            InputStream inputStream = loadDocById(doc.getId());
            IOUtils.copy(inputStream, zipOutputStream);
            inputStream.close();
        }
        zipOutputStream.closeEntry();
        zipOutputStream.flush();
        zipOutputStream.close();
    }

    @Override
    public List<DeviceRent> findRentByQueryParam(Map<String, Object> queryParam) {
        return deviceMapper.findRentByQueryParam(queryParam);
    }

    @Override
    public Long countDeviceRent() {
        return deviceMapper.countDeviceRent();
    }

    @Override
    public Long filterCountRent(Map<String, Object> queryParam) {
        return deviceMapper.filterCountRent(queryParam);
    }

}
