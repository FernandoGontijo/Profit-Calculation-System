package com.dachser.assessment.profit_calculator.service.impl;

import com.dachser.assessment.profit_calculator.dto.response.ProfitLossResponseDto;
import com.dachser.assessment.profit_calculator.exception.NotFoundException;
import com.dachser.assessment.profit_calculator.mapper.ProfitLossMapper;
import com.dachser.assessment.profit_calculator.entity.Cost;
import com.dachser.assessment.profit_calculator.entity.Income;
import com.dachser.assessment.profit_calculator.entity.ProfitLoss;
import com.dachser.assessment.profit_calculator.entity.Shipment;
import com.dachser.assessment.profit_calculator.repository.CostRepository;
import com.dachser.assessment.profit_calculator.repository.IncomeRepository;
import com.dachser.assessment.profit_calculator.repository.ProfitLossRepository;
import com.dachser.assessment.profit_calculator.repository.ShipmentRepository;
import com.dachser.assessment.profit_calculator.service.ProfitLossService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProfitLossServiceImpl implements ProfitLossService {

    private final ProfitLossRepository profitLossRepository;
    private final ShipmentRepository shipmentRepository;
    private final IncomeRepository incomeRepository;
    private final CostRepository costRepository;
    private final ProfitLossMapper profitLossMapper;


    @Override
    public ProfitLossResponseDto calculate(Long shipmentId) {

        Shipment shipment = shipmentRepository.findByIdAndActiveTrue(shipmentId)
                .orElseThrow(() -> new NotFoundException("Shipment not found with ID: " + shipmentId));

        List<Income> income = incomeRepository.findAllByShipment_IdAndActiveTrue(shipment.getId());
        List<Cost> cost = costRepository.findAllByShipment_IdAndActiveTrue(shipment.getId());


        BigDecimal totalIncome = income.stream()
                .map(Income::getAmount)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);


        BigDecimal totalCost = cost.stream()
                .map(Cost::getAmount)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);


        ProfitLoss profitLoss = new ProfitLoss();
        profitLoss.setShipment(shipment);
        profitLoss.setTotalIncome(totalIncome);
        profitLoss.setTotalCost(totalCost);
        profitLoss.setCalculatedProfit(totalIncome.subtract(totalCost));

        return profitLossMapper.toDto(profitLoss);
    }
}
