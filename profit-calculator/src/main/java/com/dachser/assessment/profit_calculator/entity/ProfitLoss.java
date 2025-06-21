package com.dachser.assessment.profit_calculator.entity;


import com.dachser.assessment.profit_calculator.entity.audit.AuditEntity;
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
public class ProfitLoss extends AuditEntity {

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

    private boolean active;
}
