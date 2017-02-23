package com.kaishengit.service.Impl;

import com.google.common.collect.Lists;
import com.kaishengit.dto.DeviceRentDto;
import com.kaishengit.exception.ServiceException.ServiceException;
import com.kaishengit.mapper.DeviceRentDocMapper;
import com.kaishengit.mapper.FinanceMapper;
import com.kaishengit.mapper.RentMapper;
import com.kaishengit.mapper.WorkOutMapper;
import com.kaishengit.pojo.*;
import com.kaishengit.service.WorkTypeService;
import com.kaishengit.shiro.ShiroUtil;
import com.kaishengit.util.SerialNumberUtil;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WorkTypeServiceImpl implements WorkTypeService {

    @Autowired
    private WorkOutMapper workOutMapper;

    @Autowired
    private RentMapper rentMapper;

    @Autowired
    private DeviceRentDocMapper docMapper;

    @Autowired
    private FinanceMapper financeMapper;

    @Override
    public WorkType findDeviceById(Integer id) {
        return workOutMapper.findWorkTypeById(id);
    }

    @Override
    public List<WorkType> findAllWorkType() {
        return workOutMapper.findAllWorkType();
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
        List<WorkTypeDetail> detailList = Lists.newArrayList();

        for (DeviceRentDto.DeviceArrayBean bean : deviceArray) {
            //查询库存
            WorkType workType = findDeviceById(bean.getId());
            if (workType.getCurrentNum() < bean.getNum()) {
                throw new ServiceException(bean.getName()+" 库存不足");
            } else {
                workType.setCurrentNum((int) (workType.getCurrentNum() - bean.getNum()));
                workOutMapper.updateCurrentNum(workType);
            }


            WorkTypeDetail workTypeDetail = new WorkTypeDetail();
            workTypeDetail.setWorkTypeName(bean.getName());
            workTypeDetail.setWorkTypePrice(bean.getPrice());
//            deviceRentDetail.setDeviceUnit(bean.getUnit());
            workTypeDetail.setNum(bean.getNum());
            workTypeDetail.setTotalPrice(bean.getTotal());
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

        //保存预付款财务数据
        Finance preFinance = new Finance();

        preFinance.setFinanceSerial(SerialNumberUtil.getFinanceSerial());
        preFinance.setSerialNum(rent.getSerialNum());
        preFinance.setType(Finance.TYPE_INCOME);
        preFinance.setCreateDate(DateTime.now().toString("yyyy-MM-dd"));
        preFinance.setCreateUser(ShiroUtil.getCurrentUserName());
        preFinance.setModule(Finance.MODULE_WORKOUT);
        preFinance.setState(Finance.STATE_UNCOMPLETE);
        preFinance.setMoney(rent.getPreCost());
        preFinance.setRemark(Finance.REMARK_PRE);

        financeMapper.saveDeviceRentFinance(preFinance);

        //返回财务流水
        return rent.getSerialNum();
    }

    @Override
    public List<WorkTypeDetail> findRentDetailByRentId(Integer id) {
        return workOutMapper.findDetailByRentId(id);
    }

    @Override
    public void addWorkType(WorkType workType) {
        workType.setCurrentNum(workType.getTotalNum());
        workOutMapper.addWorkType(workType);
    }
}
