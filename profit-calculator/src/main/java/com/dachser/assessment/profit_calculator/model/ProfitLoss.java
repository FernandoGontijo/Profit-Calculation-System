package com.dachser.assessment.profit_calculator.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "profit_loss")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfitLoss {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "shipment_id", nullable = false)
    private Shipment shipment;

    private BigDecimal totalIncome;
    private BigDecimal totalCost;
    private BigDecimal calculatedProfit;

    private LocalDateTime calculatedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private boolean active;
}
