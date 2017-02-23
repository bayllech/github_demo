package com.kaishengit.service;

import com.kaishengit.pojo.Finance;

import java.util.List;

public interface FinanceService {

    List<Finance> findAll();

    List<Finance> findDay();
}
