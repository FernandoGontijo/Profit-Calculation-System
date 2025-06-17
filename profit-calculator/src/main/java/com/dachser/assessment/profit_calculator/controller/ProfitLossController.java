package com.dachser.assessment.profit_calculator.controller;


import com.dachser.assessment.profit_calculator.service.ProfitLossService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/profit-loss")
@RestController
@RequiredArgsConstructor
public class ProfitLossController {

    private final ProfitLossService profitLossService;


}
