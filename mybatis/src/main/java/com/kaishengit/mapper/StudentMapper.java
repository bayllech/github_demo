package com.kaishengit.mapper;

import com.kaishengit.pojo.Student;

import java.util.Map;

/**
 * Created by bayllech on 2017/1/5.
 */
public interface StudentMapper {
    Student findById(Integer id);
    void save(Student student);
    Student findByParam(Map<String,Object> param);
}
