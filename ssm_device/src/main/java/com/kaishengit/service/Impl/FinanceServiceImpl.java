package com.kaishengit.service.Impl;

import com.kaishengit.mapper.FinanceMapper;
import com.kaishengit.pojo.Finance;
import com.kaishengit.service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FinanceServiceImpl implements FinanceService {

    @Autowired
    private FinanceMapper financeMapper;

    @Override
    public List<Finance> findAll() {
        return financeMapper.findAll();
    }

    @Override
    public List<Finance> findDay() {
        return financeMapper.findDay();
    }

    @Override
    public List<Finance> findFinanceByParam(Map<String, Object> queryParam) {
        return financeMapper.findFinanceByParam(queryParam);
    }

    @Override
    public Long count() {
        return financeMapper.count();
    }

    @Override
    public Long filterCount(Map<String, Object> queryParam) {
        return financeMapper.filterCount(queryParam);
    }

    @Override
    public Finance findFinanceById(Integer id) {
        return financeMapper.findById(id);
    }

    @Override
    public void updateState(Finance finance) {
        financeMapper.updateState(finance);
    }
}
