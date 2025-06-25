package com.dachser.assessment.profit_calculator.shipment;

import com.dachser.assessment.profit_calculator.dto.request.ShipmentRequestDto;
import com.dachser.assessment.profit_calculator.dto.response.ShipmentResponseDto;
import com.dachser.assessment.profit_calculator.entity.Shipment;
import com.dachser.assessment.profit_calculator.enums.ShipmentStatusEnum;
import com.dachser.assessment.profit_calculator.exception.NotFoundException;
import com.dachser.assessment.profit_calculator.mapper.ShipmentMapper;
import com.dachser.assessment.profit_calculator.repository.ShipmentRepository;
import com.dachser.assessment.profit_calculator.service.impl.ShipmentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ShipmentServiceImplTest {

    @Mock
    private ShipmentRepository shipmentRepository;

    @Mock
    private ShipmentMapper shipmentMapper;

    @InjectMocks
    private ShipmentServiceImpl shipmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        Shipment shipment = new Shipment();
        Shipment savedShipment = new Shipment();
        ShipmentResponseDto responseDto = new ShipmentResponseDto();

        when(shipmentRepository.save(any(Shipment.class))).thenReturn(savedShipment);
        when(shipmentMapper.toDto(savedShipment)).thenReturn(responseDto);

        ShipmentResponseDto result = shipmentService.create();

        assertNotNull(result);
        verify(shipmentRepository).save(any(Shipment.class));
        verify(shipmentMapper).toDto(savedShipment);
    }

    @Test
    void testGetById_Success() {
        Shipment shipment = new Shipment();
        ShipmentResponseDto responseDto = new ShipmentResponseDto();

        when(shipmentRepository.findByIdAndActiveTrue(1L)).thenReturn(Optional.of(shipment));
        when(shipmentMapper.toDto(shipment)).thenReturn(responseDto);

        ShipmentResponseDto result = shipmentService.getById(1L);

        assertNotNull(result);
        verify(shipmentRepository).findByIdAndActiveTrue(1L);
        verify(shipmentMapper).toDto(shipment);
    }

    @Test
    void testGetById_NotFound() {
        when(shipmentRepository.findByIdAndActiveTrue(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> shipmentService.getById(1L));
    }

    @Test
    void testGetAll() {
        List<Shipment> shipments = List.of(new Shipment(), new Shipment());
        List<ShipmentResponseDto> dtos = List.of(new ShipmentResponseDto(), new ShipmentResponseDto());

        when(shipmentRepository.findAllByActiveTrue()).thenReturn(shipments);
        when(shipmentMapper.toDto(any())).thenReturn(new ShipmentResponseDto());

        List<ShipmentResponseDto> result = shipmentService.getAll();

        assertEquals(2, result.size());
        verify(shipmentRepository).findAllByActiveTrue();
    }

    @Test
    void testUpdate_Success() {
        ShipmentRequestDto request = new ShipmentRequestDto();
        request.setId(1L);
        request.setStatus("CREATED");

        Shipment existing = new Shipment();
        Shipment updated = new Shipment();
        ShipmentResponseDto responseDto = new ShipmentResponseDto();

        when(shipmentRepository.findByIdAndActiveTrue(1L)).thenReturn(Optional.of(existing));
        when(shipmentRepository.save(existing)).thenReturn(updated);
        when(shipmentMapper.toDto(updated)).thenReturn(responseDto);

        ShipmentResponseDto result = shipmentService.update(request);

        assertNotNull(result);
        verify(shipmentRepository).findByIdAndActiveTrue(1L);
        verify(shipmentRepository).save(existing);
    }

    @Test
    void testUpdate_NotFound() {
        ShipmentRequestDto request = new ShipmentRequestDto();
        request.setId(1L);
        request.setStatus("CREATED");

        when(shipmentRepository.findByIdAndActiveTrue(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> shipmentService.update(request));
    }

    @Test
    void testDelete_Success() {
        Shipment shipment = new Shipment();
        shipment.setActive(true);

        when(shipmentRepository.findByIdAndActiveTrue(1L)).thenReturn(Optional.of(shipment));

        shipmentService.delete(1L);

        assertFalse(shipment.isActive());
        verify(shipmentRepository).save(shipment);
    }

    @Test
    void testDelete_NotFound() {
        when(shipmentRepository.findByIdAndActiveTrue(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> shipmentService.delete(1L));
    }
}
