package com.dachser.assessment.profit_calculator.service.impl;

import com.dachser.assessment.profit_calculator.dto.response.ProfitLossResponseDto;
import com.dachser.assessment.profit_calculator.entity.Cost;
import com.dachser.assessment.profit_calculator.entity.Income;
import com.dachser.assessment.profit_calculator.entity.ProfitLoss;
import com.dachser.assessment.profit_calculator.entity.Shipment;
import com.dachser.assessment.profit_calculator.exception.NotFoundException;
import com.dachser.assessment.profit_calculator.mapper.ProfitLossMapper;
import com.dachser.assessment.profit_calculator.repository.CostRepository;
import com.dachser.assessment.profit_calculator.repository.IncomeRepository;
import com.dachser.assessment.profit_calculator.repository.ProfitLossRepository;
import com.dachser.assessment.profit_calculator.repository.ShipmentRepository;
import com.dachser.assessment.profit_calculator.service.ProfitLossService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

        deactivateExistingProfitLoss(shipmentId);

        Shipment shipment = shipmentRepository.findByIdAndActiveTrue(shipmentId)
                .orElseThrow(() -> new NotFoundException("Shipment not found with ID: " + shipmentId));



        return profitLossMapper.toDto(calculate(shipment));
    }


    private void deactivateExistingProfitLoss(Long shipmentId) {
        profitLossRepository.findByShipment_IdAndActiveTrue(shipmentId)
                .ifPresent(profitLoss -> {
                    profitLoss.setActive(false);
                    profitLossRepository.save(profitLoss);
                });
    }

    @Override
    public Page<ProfitLossResponseDto> getAllProfitLoss(Pageable pageable) {

        Pageable sortedPageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(Sort.Direction.DESC, "createdAt")
        );

        return profitLossRepository.findAllByActiveTrue(sortedPageable)
                .map(this::calculateAndMap);
    }

    @Override
    public void delete(Long id) {
        Optional<ProfitLoss> profitLossOptional = profitLossRepository.findById(id);

        if (profitLossOptional.isPresent()) {
            ProfitLoss profitLoss = profitLossOptional.get();
            profitLoss.setActive(false);
            profitLossRepository.save(profitLoss);
        }
    }

    private ProfitLossResponseDto calculateAndMap(ProfitLoss profitLoss) {
        return profitLossMapper.toDto(profitLoss);
    }

    private ProfitLoss calculate(Shipment shipment) {

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
        profitLoss.setCalculatedAt(LocalDateTime.now());

        profitLossRepository.save(profitLoss);

        return profitLoss;

    }
}
