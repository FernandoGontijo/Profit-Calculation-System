package com.dachser.assessment.profit_calculator.income;

import com.dachser.assessment.profit_calculator.dto.request.IncomeRequestDto;
import com.dachser.assessment.profit_calculator.dto.response.IncomeResponseDto;
import com.dachser.assessment.profit_calculator.entity.Income;
import com.dachser.assessment.profit_calculator.entity.Shipment;
import com.dachser.assessment.profit_calculator.exception.NotFoundException;
import com.dachser.assessment.profit_calculator.mapper.IncomeMapper;
import com.dachser.assessment.profit_calculator.repository.IncomeRepository;
import com.dachser.assessment.profit_calculator.repository.ShipmentRepository;
import com.dachser.assessment.profit_calculator.service.impl.IncomeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IncomeServiceImplTest {

    @Mock
    private IncomeRepository incomeRepository;

    @Mock
    private ShipmentRepository shipmentRepository;

    @Mock
    private IncomeMapper incomeMapper;

    @InjectMocks
    private IncomeServiceImpl incomeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAll_ReturnsMappedList() {
        Income income = new Income();
        IncomeResponseDto dto = new IncomeResponseDto();
        when(incomeRepository.findAllByActiveTrue()).thenReturn(List.of(income));
        when(incomeMapper.toDto(income)).thenReturn(dto);

        List<IncomeResponseDto> result = incomeService.getAll();

        assertEquals(1, result.size());
        verify(incomeRepository).findAllByActiveTrue();
    }

    @Test
    void create_ValidRequest_ReturnsSavedDto() {
        IncomeRequestDto request = new IncomeRequestDto(1L, 1L, BigDecimal.ZERO);
        Shipment shipment = new Shipment();
        Income income = new Income();
        Income savedIncome = new Income();
        IncomeResponseDto responseDto = new IncomeResponseDto();

        when(shipmentRepository.findById(1L)).thenReturn(Optional.of(shipment));
        when(incomeMapper.toEntity(request)).thenReturn(income);
        when(incomeRepository.save(income)).thenReturn(savedIncome);
        when(incomeMapper.toDto(savedIncome)).thenReturn(responseDto);

        IncomeResponseDto result = incomeService.create(request);

        assertEquals(responseDto, result);
    }

    @Test
    void getById_ExistingId_ReturnsDto() {
        Income income = new Income();
        IncomeResponseDto dto = new IncomeResponseDto();
        when(incomeRepository.findById(1L)).thenReturn(Optional.of(income));
        when(incomeMapper.toDto(income)).thenReturn(dto);

        IncomeResponseDto result = incomeService.getById(1L);

        assertEquals(dto, result);
    }

    @Test
    void getById_NonExistingId_ThrowsNotFound() {
        when(incomeRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> incomeService.getById(1L));
    }

    @Test
    void update_ValidRequest_ReturnsUpdatedDto() {
        IncomeRequestDto request = new IncomeRequestDto(1L, 1L, BigDecimal.ONE);
        Income income = new Income();
        Shipment shipment = new Shipment();
        Income updated = new Income();
        IncomeResponseDto dto = new IncomeResponseDto();

        when(incomeRepository.findById(1L)).thenReturn(Optional.of(income));
        when(shipmentRepository.findById(1L)).thenReturn(Optional.of(shipment));
        when(incomeRepository.save(income)).thenReturn(updated);
        when(incomeMapper.toDto(updated)).thenReturn(dto);

        IncomeResponseDto result = incomeService.update(request);

        assertEquals(dto, result);
    }

    @Test
    void delete_ExistingId_SetsInactive() {
        Income income = new Income();
        income.setActive(true);
        when(incomeRepository.findById(1L)).thenReturn(Optional.of(income));

        incomeService.delete(1L);

        assertFalse(income.isActive());
        verify(incomeRepository).save(income);
    }
}
