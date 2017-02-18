package com.kaishengit.mapper;


import com.kaishengit.pojo.WorkType;

import java.util.List;

public interface WorkOutMapper {
    List<WorkType> findAllWorkType();

    WorkType findWorkTypeById(Integer id);
}
