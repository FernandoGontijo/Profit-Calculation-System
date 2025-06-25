package com.dachser.assessment.profit_calculator.profitloss;

import com.dachser.assessment.profit_calculator.dto.response.ProfitLossResponseDto;
import com.dachser.assessment.profit_calculator.entity.Cost;
import com.dachser.assessment.profit_calculator.entity.Income;
import com.dachser.assessment.profit_calculator.entity.ProfitLoss;
import com.dachser.assessment.profit_calculator.entity.Shipment;
import com.dachser.assessment.profit_calculator.exception.NotFoundException;
import com.dachser.assessment.profit_calculator.mapper.ProfitLossMapper;
import com.dachser.assessment.profit_calculator.repository.CostRepository;
import com.dachser.assessment.profit_calculator.repository.IncomeRepository;
import com.dachser.assessment.profit_calculator.repository.ProfitLossRepository;
import com.dachser.assessment.profit_calculator.repository.ShipmentRepository;
import com.dachser.assessment.profit_calculator.service.impl.ProfitLossServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProfitLossServiceImplTest {

    @Mock
    private ProfitLossRepository profitLossRepository;

    @Mock
    private ShipmentRepository shipmentRepository;

    @Mock
    private IncomeRepository incomeRepository;

    @Mock
    private CostRepository costRepository;

    @Mock
    private ProfitLossMapper profitLossMapper;

    @InjectMocks
    private ProfitLossServiceImpl profitLossService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void calculate_shouldReturnProfitLossResponseDto() {
        Long shipmentId = 1L;
        Shipment shipment = new Shipment();
        shipment.setId(shipmentId);

        Income income = new Income();
        income.setAmount(BigDecimal.valueOf(200));
        Cost cost = new Cost();
        cost.setAmount(BigDecimal.valueOf(50));

        ProfitLoss savedProfitLoss = new ProfitLoss();
        savedProfitLoss.setCalculatedProfit(BigDecimal.valueOf(150));
        savedProfitLoss.setTotalIncome(income.getAmount());
        savedProfitLoss.setTotalCost(cost.getAmount());
        savedProfitLoss.setShipment(shipment);
        savedProfitLoss.setCalculatedAt(LocalDateTime.now());

        ProfitLossResponseDto expectedDto = new ProfitLossResponseDto();

        when(profitLossRepository.findByShipment_IdAndActiveTrue(shipmentId)).thenReturn(Optional.empty());
        when(shipmentRepository.findByIdAndActiveTrue(shipmentId)).thenReturn(Optional.of(shipment));
        when(incomeRepository.findAllByShipment_IdAndActiveTrue(shipmentId)).thenReturn(List.of(income));
        when(costRepository.findAllByShipment_IdAndActiveTrue(shipmentId)).thenReturn(List.of(cost));
        when(profitLossRepository.save(any(ProfitLoss.class))).thenReturn(savedProfitLoss);
        when(profitLossMapper.toDto(any(ProfitLoss.class))).thenReturn(expectedDto);

        ProfitLossResponseDto result = profitLossService.calculate(shipmentId);

        assertEquals(expectedDto, result);
    }

    @Test
    void calculate_shouldThrowNotFoundException_whenShipmentNotFound() {
        Long shipmentId = 1L;
        when(profitLossRepository.findByShipment_IdAndActiveTrue(shipmentId)).thenReturn(Optional.empty());
        when(shipmentRepository.findByIdAndActiveTrue(shipmentId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> profitLossService.calculate(shipmentId));
    }

    @Test
    void getAllProfitLoss_shouldReturnPagedResults() {
        ProfitLoss profitLoss = new ProfitLoss();
        Page<ProfitLoss> page = new PageImpl<>(List.of(profitLoss));
        ProfitLossResponseDto dto = new ProfitLossResponseDto();

        when(profitLossRepository.findAllByActiveTrue(any(Pageable.class))).thenReturn(page);
        when(profitLossMapper.toDto(any(ProfitLoss.class))).thenReturn(dto);

        Pageable pageable = PageRequest.of(0, 5);
        Page<ProfitLossResponseDto> result = profitLossService.getAllProfitLoss(pageable);

        assertEquals(1, result.getTotalElements());
        assertEquals(dto, result.getContent().get(0));
    }

    @Test
    void delete_shouldDeactivateProfitLoss() {
        Long id = 1L;
        ProfitLoss profitLoss = new ProfitLoss();
        profitLoss.setActive(true);

        when(profitLossRepository.findById(id)).thenReturn(Optional.of(profitLoss));

        profitLossService.delete(id);

        assertFalse(profitLoss.isActive());
        verify(profitLossRepository, times(1)).save(profitLoss);
    }

    @Test
    void delete_shouldDoNothing_whenProfitLossNotFound() {
        when(profitLossRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> profitLossService.delete(99L));
    }
}
