package com.dachser.assessment.profit_calculator.service;

import com.dachser.assessment.profit_calculator.dto.request.ShipmentRequestDto;
import com.dachser.assessment.profit_calculator.dto.response.ShipmentResponseDto;

import java.util.List;

public interface ShipmentService {

    public List<ShipmentResponseDto> getAllShipments();

    public ShipmentResponseDto getShipment(Long id);

    public Long create(ShipmentRequestDto shipmentRequestDto);

    public void delete(Long id);
}
