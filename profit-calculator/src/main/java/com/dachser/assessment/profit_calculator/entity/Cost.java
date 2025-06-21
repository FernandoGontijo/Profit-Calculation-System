package com.dachser.assessment.profit_calculator.entity;


import com.dachser.assessment.profit_calculator.entity.audit.AuditEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "costs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cost extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "shipment_id", nullable = false)
    private Shipment shipment;

    private BigDecimal amount;

    @Column(nullable = false)
    private boolean active = true;
}
