package com.kaishengit.service;

import com.kaishengit.pojo.Finance;

import java.util.List;
import java.util.Map;

public interface FinanceService {

    List<Finance> findAll();

    List<Finance> findDay();

    List<Finance> findFinanceByParam(Map<String, Object> queryParam);

    Long count();

    Long filterCount(Map<String, Object> queryParam);

    Finance findFinanceById(Integer id);

    void updateState(Finance finance);

    List<Map<String,Object>> getDayData(String type, String today);
}
