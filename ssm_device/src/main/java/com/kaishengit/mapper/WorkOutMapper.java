package com.kaishengit.mapper;

import com.kaishengit.pojo.WorkType;
import com.kaishengit.pojo.WorkTypeDetail;

import java.util.List;

public interface WorkOutMapper {
    List<WorkType> findAllWorkType();

    WorkType findWorkTypeById(Integer id);

    void saveDetail(List<WorkTypeDetail> detailList);

    List<WorkTypeDetail> findDetailByRentId(Integer rentId);

    void addWorkType(WorkType workType);

    WorkType findWorkTypeByName(String workTypeName);

    void updateWorkTypeCurrent(WorkType workType);

    void updateCurrentNum(WorkType workType);
}
