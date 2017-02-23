package com.kaishengit.controller;

import com.kaishengit.pojo.Finance;
import com.kaishengit.service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/finance")
public class FinanceController {

    @Autowired
    private FinanceService financeService;

    /**
     * 日报
     * @param model
     * @return
     */
    @GetMapping("/day")
    public String manage(Model model) {
        List<Finance> financeList = financeService.findDay();
        model.addAttribute("financeList", financeList);
        return "/finance/day";
    }


}
