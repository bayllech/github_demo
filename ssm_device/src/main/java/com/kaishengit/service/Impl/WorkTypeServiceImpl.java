package com.kaishengit.service.Impl;

import com.google.common.collect.Lists;
import com.kaishengit.dto.DeviceRentDto;
import com.kaishengit.mapper.DeviceRentDocMapper;
import com.kaishengit.mapper.RentMapper;
import com.kaishengit.mapper.WorkOutMapper;
import com.kaishengit.pojo.*;
import com.kaishengit.service.WorkTypeService;
import com.kaishengit.util.SerialNumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkTypeServiceImpl implements WorkTypeService {

    @Autowired
    private WorkOutMapper workOutMapper;

    @Autowired
    private RentMapper rentMapper;

    @Autowired
    private DeviceRentDocMapper docMapper;

    @Override
    public WorkType findDeviceById(Integer id) {
        return workOutMapper.findWorkTypeById(id);
    }

    @Override
    public List<WorkType> findAllWorkType() {
        return workOutMapper.findAllWorkType();
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
        List<WorkTypeDetail> detailList = Lists.newArrayList();

        for (DeviceRentDto.DeviceArrayBean bean : deviceArray) {
            WorkTypeDetail workTypeDetail = new WorkTypeDetail();
            workTypeDetail.setWorkTypeName(bean.getName());
            workTypeDetail.setWorkTypePrice(bean.getPrice());
//            deviceRentDetail.setDeviceUnit(bean.getUnit());
            workTypeDetail.setNum(bean.getNum());
            workTypeDetail.setTotalPrice(bean.getPrice());
            workTypeDetail.setRentId(rent.getId());

            detailList.add(workTypeDetail);

        }
        if (!detailList.isEmpty()) {
            workOutMapper.saveDetail(detailList);
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
}
