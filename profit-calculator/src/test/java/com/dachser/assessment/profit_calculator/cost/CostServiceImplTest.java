package com.dachser.assessment.profit_calculator.cost;

import com.dachser.assessment.profit_calculator.dto.request.CostRequestDto;
import com.dachser.assessment.profit_calculator.dto.response.CostResponseDto;
import com.dachser.assessment.profit_calculator.entity.Cost;
import com.dachser.assessment.profit_calculator.entity.Shipment;
import com.dachser.assessment.profit_calculator.exception.NotFoundException;
import com.dachser.assessment.profit_calculator.mapper.CostMapper;
import com.dachser.assessment.profit_calculator.repository.CostRepository;
import com.dachser.assessment.profit_calculator.repository.ShipmentRepository;
import com.dachser.assessment.profit_calculator.service.impl.CostServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class CostServiceImplTest {

    @Mock
    private CostRepository costRepository;

    @Mock
    private ShipmentRepository shipmentRepository;

    @Mock
    private CostMapper costMapper;

    @InjectMocks
    private CostServiceImpl costService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAll() {
        Cost cost = new Cost();
        CostResponseDto responseDto = new CostResponseDto();

        when(costRepository.findAllByActiveTrue()).thenReturn(List.of(cost));
        when(costMapper.toDto(cost)).thenReturn(responseDto);

        List<CostResponseDto> result = costService.getAll();

        assertThat(result).hasSize(1).contains(responseDto);
    }

    @Test
    void testCreate_Success() {
        CostRequestDto request = new CostRequestDto(1L, 1L, BigDecimal.valueOf(1233L));
        Shipment shipment = new Shipment();
        Cost cost = new Cost();
        Cost savedCost = new Cost();
        CostResponseDto responseDto = new CostResponseDto();

        when(shipmentRepository.findById(1L)).thenReturn(Optional.of(shipment));
        when(costMapper.toEntity(request)).thenReturn(cost);
        when(costRepository.save(cost)).thenReturn(savedCost);
        when(costMapper.toDto(savedCost)).thenReturn(responseDto);

        CostResponseDto result = costService.create(request);

        assertThat(result).isEqualTo(responseDto);
        verify(costRepository).save(cost);
    }

    @Test
    void testCreate_ShipmentNotFound() {
        CostRequestDto request = new CostRequestDto(99L, 1L, BigDecimal.ZERO);

        when(shipmentRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> costService.create(request))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("Shipment not found");
    }

    @Test
    void testGetById_Success() {
        Cost cost = new Cost();
        CostResponseDto responseDto = new CostResponseDto();

        when(costRepository.findById(1L)).thenReturn(Optional.of(cost));
        when(costMapper.toDto(cost)).thenReturn(responseDto);

        CostResponseDto result = costService.getById(1L);
        assertThat(result).isEqualTo(responseDto);
    }

    @Test
    void testGetById_NotFound() {
        when(costRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> costService.getById(1L))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("Cost not found");
    }

    @Test
    void testUpdate_Success() {
        CostRequestDto request = new CostRequestDto(1L, 1L, BigDecimal.ZERO);
        Cost cost = new Cost();
        Cost updated = new Cost();
        CostResponseDto responseDto = new CostResponseDto();

        when(costRepository.findById(1L)).thenReturn(Optional.of(cost));
        when(costRepository.save(cost)).thenReturn(updated);
        when(costMapper.toDto(updated)).thenReturn(responseDto);

        CostResponseDto result = costService.update(request);
        assertThat(result).isEqualTo(responseDto);
        verify(costRepository).save(cost);
    }

    @Test
    void testDelete_Success() {
        Cost cost = new Cost();
        cost.setActive(true);

        when(costRepository.findById(1L)).thenReturn(Optional.of(cost));

        costService.delete(1L);

        assertThat(cost.isActive()).isFalse();
        verify(costRepository).save(cost);
    }
}
