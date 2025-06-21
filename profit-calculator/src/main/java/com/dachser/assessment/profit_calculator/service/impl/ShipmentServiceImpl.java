package com.dachser.assessment.profit_calculator.service.impl;

import com.dachser.assessment.profit_calculator.dto.request.ShipmentRequestDto;
import com.dachser.assessment.profit_calculator.dto.response.ShipmentResponseDto;
import com.dachser.assessment.profit_calculator.enums.ShipmentStatusEnum;
import com.dachser.assessment.profit_calculator.exception.NotFoundException;
import com.dachser.assessment.profit_calculator.mapper.ShipmentMapper;
import com.dachser.assessment.profit_calculator.entity.Shipment;
import com.dachser.assessment.profit_calculator.repository.ShipmentRepository;
import com.dachser.assessment.profit_calculator.service.ShipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShipmentServiceImpl implements ShipmentService {

    private final ShipmentRepository shipmentRepository;
    private final ShipmentMapper shipmentMapper;

    @Override
    public ShipmentResponseDto create() {
        Shipment shipment = new Shipment();
        shipment.setActive(true);
        Shipment shipmentCreated = shipmentRepository.save(shipment);
        return shipmentMapper.toDto(shipmentCreated);
    }

    @Override
    public ShipmentResponseDto getById(Long shipmentId) {
        Shipment shipment = shipmentRepository.findByIdAndActiveTrue(shipmentId)
                .orElseThrow(() -> new NotFoundException("Shipment not found"));
        return shipmentMapper.toDto(shipment);
    }

    @Override
    public List<ShipmentResponseDto> getAll() {
        List<Shipment> shipments = shipmentRepository.findAllByActiveTrue();
        return shipments.stream().map(shipmentMapper::toDto).toList();
    }

    @Override
    public ShipmentResponseDto update(ShipmentRequestDto request) {
        Shipment existing = shipmentRepository.findByIdAndActiveTrue(request.getId())
                .orElseThrow(() -> new NotFoundException("Shipment not found"));

        existing.setStatus(ShipmentStatusEnum.valueOf(request.getStatus()));
        Shipment updated = shipmentRepository.save(existing);
        return shipmentMapper.toDto(updated);
    }

    @Override
    public void delete(Long id) {
        Shipment existing = shipmentRepository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new NotFoundException("Shipment not found"));
        existing.setActive(false);
        shipmentRepository.save(existing);
    }
}
