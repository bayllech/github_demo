package com.kaishengit.mapper;

import com.kaishengit.pojo.Finance;

import java.util.List;

public interface FinanceMapper {
    List<Finance> findAll();

    void saveDeviceRentFinance(Finance financeList);

    Finance findByRentSerial(String serialNum);
}
