package com.kaishengit.mapper;

import com.kaishengit.pojo.Student;

import java.util.List;
import java.util.Map;

/**
 * Created by bayllech on 2017/1/5.
 */
public interface StudentMapper {
    Student findById(Integer id);
    void save(Student student);
    Student findByParam(Map<String,Object> param);
    void update(Map<String, Object> param);
    List<Student> findByIds(List<Integer> idList);
    void batchSave(List<Student> list);
}
