package com.dachser.assessment.profit_calculator.controller;

import com.dachser.assessment.profit_calculator.service.CostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/api/v1/cost")
@RestController
@RequiredArgsConstructor
public class CostController {

    private final CostService costService;



}
