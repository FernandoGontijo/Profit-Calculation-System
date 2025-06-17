package com.dachser.assessment.profit_calculator.controller;


import com.dachser.assessment.profit_calculator.service.IncomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/api/v1/income")
@RestController
@RequiredArgsConstructor
public class IncomeController {


    private final IncomeService incomeService;




}
