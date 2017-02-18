package com.kaishengit.service.Impl;

import com.kaishengit.mapper.WorkOutMapper;
import com.kaishengit.pojo.WorkType;
import com.kaishengit.service.WorkTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkTypeServiceImpl implements WorkTypeService {

    @Autowired
    private WorkOutMapper workOutMapper;

    @Override
    public WorkType findDeviceById(Integer id) {
        return workOutMapper.findWorkTypeById(id);
    }

    @Override
    public List<WorkType> findAllWorkType() {
        return workOutMapper.findAllWorkType();
    }
}
