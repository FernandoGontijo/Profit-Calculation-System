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
        List<ShipmentResponseDto> shipments =  shipmentService.getAll();
        return ResponseEntity.ok(shipments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShipmentResponseDto> getShipment(@PathVariable Long id) {
        ShipmentResponseDto shipment =  shipmentService.getById(id);
        return ResponseEntity.ok(shipment);
    }

    @PostMapping
    public ResponseEntity<Void> create() {
        Long id = shipmentService.create().getId();
        return ResponseEntity.created(URI.create("/api/v1/shipment/" + id)).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        shipmentService.delete(id);
        return ResponseEntity.created(URI.create("/api/v1/shipment/" + id)).build();
    }

}
