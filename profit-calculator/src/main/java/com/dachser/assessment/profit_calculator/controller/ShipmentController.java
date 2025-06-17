package com.dachser.assessment.profit_calculator.controller;


import com.dachser.assessment.profit_calculator.dto.request.ShipmentRequestDto;
import com.dachser.assessment.profit_calculator.dto.response.ShipmentResponseDto;
import com.dachser.assessment.profit_calculator.service.ShipmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequestMapping("/api/v1/shipment")
@RestController
@RequiredArgsConstructor
public class ShipmentController {


    private final ShipmentService shipmentService;

    @GetMapping
    public ResponseEntity<List<ShipmentResponseDto>> getAllShipments() {
        List<ShipmentResponseDto> shipments =  shipmentService.getAllShipments();
        return ResponseEntity.ok(shipments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShipmentResponseDto> getShipment(@PathVariable Long id) {
        ShipmentResponseDto shipment =  shipmentService.getShipment(id);
        return ResponseEntity.ok(shipment);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid ShipmentRequestDto shipmentRequestDto) {
        Long id = shipmentService.create(shipmentRequestDto);
        return ResponseEntity.created(URI.create("/api/v1/shipment/" + id)).build();
    }

}
