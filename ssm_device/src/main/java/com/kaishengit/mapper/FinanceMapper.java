package com.kaishengit.mapper;

import com.kaishengit.pojo.Finance;

import java.util.List;

public interface FinanceMapper {
    List<Finance> findAll();

    void saveDeviceRentFinance(Finance finance);

    Finance findByRentSerial(String serialNum);

    List<Finance> findDay();
}
