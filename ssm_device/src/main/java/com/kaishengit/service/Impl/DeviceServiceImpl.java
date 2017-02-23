package com.kaishengit.service.Impl;

import com.google.common.collect.Lists;
import com.kaishengit.dto.DeviceRentDto;
import com.kaishengit.exception.ServiceException.ServiceException;
import com.kaishengit.mapper.*;
import com.kaishengit.pojo.*;
import com.kaishengit.service.DeviceService;
import com.kaishengit.shiro.ShiroUtil;
import com.kaishengit.util.SerialNumberUtil;
import org.apache.commons.io.IOUtils;
import org.joda.time.DateTime;
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

    @Autowired
    private FinanceMapper financeMapper;

    @Autowired
    private WorkOutMapper workOutMapper;

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
        rent.setCreateUser(ShiroUtil.getCurrentUserName());
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
                deviceRentDetail.setTotalPrice(bean.getTotal());
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

        //保存预付款财务数据
        Finance preFinance = new Finance();

        preFinance.setFinanceSerial(SerialNumberUtil.getFinanceSerial());
        preFinance.setSerialNum(rent.getSerialNum());
        preFinance.setType(Finance.TYPE_INCOME);
        preFinance.setCreateDate(DateTime.now().toString("yyyy-MM-dd"));
        preFinance.setCreateUser(ShiroUtil.getCurrentUserName());
        preFinance.setModule(Finance.MODULE_DEVICE);
        preFinance.setState(Finance.STATE_UNCOMPLETE);
        preFinance.setMoney(rent.getPreCost());
        preFinance.setRemark(Finance.REMARK_PRE);

        financeMapper.saveDeviceRentFinance(preFinance);

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

    @Override
    @Transactional
    public void updateRentState(DeviceRent rent) {
        //工种或设备数量恢复
        List<DeviceRentDetail> deviceDetailList = detailMapper.findRentDetailByRentId(rent.getId());
        if (deviceDetailList == null) {
            for (DeviceRentDetail detail : deviceDetailList) {
                Device device = deviceMapper.findDeviceByName(detail.getDeviceName());
                device.setCurrentNum((int) (device.getCurrentNum()+detail.getNum()));
                deviceMapper.updateDeviceCurrent(device);
            }
        } else {
            List<WorkTypeDetail> workTypeDetailList = workOutMapper.findDetailByRentId(rent.getId());
            for (WorkTypeDetail detail : workTypeDetailList) {
                WorkType workType = workOutMapper.findWorkTypeByName(detail.getWorkTypeName());
                workType.setCurrentNum((int) (workType.getCurrentNum()+detail.getNum()));
                workOutMapper.updateWorkTypeCurrent(workType);
            }
        }

        //添加尾款财务数据
        Finance lastFinance = new Finance();

        lastFinance.setFinanceSerial(SerialNumberUtil.getFinanceSerial());
        lastFinance.setSerialNum(rent.getSerialNum());
        lastFinance.setType(Finance.TYPE_INCOME);
        lastFinance.setCreateDate(DateTime.now().toString("yyyy-MM-dd"));
        lastFinance.setCreateUser(ShiroUtil.getCurrentUserName());
        //查询Module业务类型
        Finance finance = financeMapper.findByRentSerial(rent.getSerialNum());
        lastFinance.setModule(finance.getModule());

        lastFinance.setState(Finance.STATE_UNCOMPLETE);
        lastFinance.setMoney(rent.getLastCost());
        lastFinance.setRemark(Finance.REMARK_LAST);

        financeMapper.saveDeviceRentFinance(lastFinance);

        deviceMapper.updateRentState(rent);
    }

}
