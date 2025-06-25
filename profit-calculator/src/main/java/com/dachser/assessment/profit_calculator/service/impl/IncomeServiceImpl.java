package com.dachser.assessment.profit_calculator.service.impl;

import com.dachser.assessment.profit_calculator.dto.request.IncomeRequestDto;
import com.dachser.assessment.profit_calculator.dto.response.IncomeResponseDto;
import com.dachser.assessment.profit_calculator.entity.Income;
import com.dachser.assessment.profit_calculator.entity.Shipment;
import com.dachser.assessment.profit_calculator.exception.NotFoundException;
import com.dachser.assessment.profit_calculator.mapper.IncomeMapper;
import com.dachser.assessment.profit_calculator.repository.IncomeRepository;
import com.dachser.assessment.profit_calculator.repository.ShipmentRepository;
import com.dachser.assessment.profit_calculator.service.IncomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IncomeServiceImpl implements IncomeService {

    private final IncomeRepository incomeRepository;
    private final ShipmentRepository shipmentRepository;
    private final IncomeMapper incomeMapper;

    @Override
    public List<IncomeResponseDto> getAll() {
        List<Income> incomes = incomeRepository.
                findAllByActiveTrue();
        return incomes.stream().map(incomeMapper::toDto).toList();
    }

    @Override
    public IncomeResponseDto create(IncomeRequestDto request) {

        Shipment shipment = shipmentRepository.findById(request.getShipmentId())
                .orElseThrow(() -> new NotFoundException("Shipment not found"));

        Income income = incomeMapper.toEntity(request);
        income.setShipment(shipment);

        Income saved = incomeRepository.save(income);
        return incomeMapper.toDto(saved);
    }

    @Override
    public IncomeResponseDto getById(Long id) {
        Income income = incomeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Income not found"));
        return incomeMapper.toDto(income);
    }

    @Override
    public IncomeResponseDto getByShipmentId(Long shipmentId) {
        List<Income> incomes = incomeRepository.findAllByShipment_IdAndActiveTrue(shipmentId);
        return incomes.stream().map(incomeMapper::toDto).findFirst().orElseThrow(() -> new NotFoundException("Income not found"));
    }

    @Override
    public IncomeResponseDto update(IncomeRequestDto request) {
        Income income = incomeRepository.findById(request.getId())
                .orElseThrow(() -> new NotFoundException("Income not found"));

        Shipment shipment = shipmentRepository.findById(request.getShipmentId())
                .orElseThrow(() -> new NotFoundException("Shipment not found"));

        income.setAmount(request.getAmount());
        income.setShipment(shipment);

        Income updated = incomeRepository.save(income);
        return incomeMapper.toDto(updated);
    }

    @Override
    public void delete(Long id) {
        Income income = incomeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Income not found"));
        income.setActive(false);
        incomeRepository.save(income);
    }
}
