package com.dachser.assessment.profit_calculator.service.impl;

import com.dachser.assessment.profit_calculator.dto.request.CostRequestDto;
import com.dachser.assessment.profit_calculator.dto.response.CostResponseDto;
import com.dachser.assessment.profit_calculator.exception.NotFoundException;
import com.dachser.assessment.profit_calculator.mapper.CostMapper;
import com.dachser.assessment.profit_calculator.entity.Cost;
import com.dachser.assessment.profit_calculator.entity.Shipment;
import com.dachser.assessment.profit_calculator.repository.CostRepository;
import com.dachser.assessment.profit_calculator.repository.ShipmentRepository;
import com.dachser.assessment.profit_calculator.service.CostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CostServiceImpl implements CostService {


    private final CostRepository costRepository;
    private final ShipmentRepository shipmentRepository;
    private final CostMapper costMapper;

    @Override
    public CostResponseDto create(CostRequestDto request) {
        Shipment shipment = shipmentRepository.findById(request.getShipment().getId())
                .orElseThrow(() -> new NotFoundException("Shipment not found"));

        Cost cost = costMapper.toEntity(request);
        cost.setShipment(shipment);

        Cost saved = costRepository.save(cost);
        return costMapper.toDto(saved);
    }

    @Override
    public CostResponseDto getById(Long id) {
        Cost cost = costRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cost not found"));
        return costMapper.toDto(cost);
    }

    @Override
    public List<CostResponseDto> getByShipmentId(Long shipmentId) {
        List<Cost> costs = costRepository.findByShipmentId(shipmentId);
        return costs.stream().map(costMapper::toDto).toList();
    }

    @Override
    public CostResponseDto update(Long id, CostRequestDto request) {
        Cost cost = costRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cost not found"));

        Shipment shipment = shipmentRepository.findById(request.getShipment().getId())
                .orElseThrow(() -> new NotFoundException("Shipment not found"));

        cost.setAmount(request.getAmount());
        cost.setShipment(shipment);

        Cost updated = costRepository.save(cost);
        return costMapper.toDto(updated);
    }

    @Override
    public void delete(Long id) {
        Cost cost = costRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cost not found"));
        cost.setActive(false);
        costRepository.save(cost);
    }
}
