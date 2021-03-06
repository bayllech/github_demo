package com.kaishengit.mapper;

import com.kaishengit.pojo.Finance;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface FinanceMapper {
    List<Finance> findAll();

    void saveDeviceRentFinance(Finance finance);

    Finance findByRentSerial(String serialNum);

    List<Finance> findDay();

    List<Finance> findFinanceByParam(Map<String, Object> queryParam);

    Long count();

    Long filterCount(Map<String, Object> queryParam);

    Finance findById(Integer id);

    void updateState(Finance finance);

    List<Map<String,Object>> getDayData(@Param("type") String type,@Param("today") String today);
}
