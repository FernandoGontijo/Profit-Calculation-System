package com.dachser.assessment.profit_calculator.service.impl;

import com.dachser.assessment.profit_calculator.dto.request.ShipmentRequestDto;
import com.dachser.assessment.profit_calculator.dto.response.ShipmentResponseDto;
import com.dachser.assessment.profit_calculator.repository.ShipmentRepository;
import com.dachser.assessment.profit_calculator.service.ShipmentService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ShipmentServiceImpl implements ShipmentService {

    private final ShipmentRepository shipmentRepository;


    @Override
    public List<ShipmentResponseDto> getAllShipments() {
        return List.of();
    }

    @Override
    public ShipmentResponseDto getShipment(Long id) {
        return null;
    }

    @Override
    public Long create(ShipmentRequestDto shipmentRequestDto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
