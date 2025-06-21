package com.dachser.assessment.profit_calculator.entity;

import com.dachser.assessment.profit_calculator.entity.audit.AuditEntity;
import com.dachser.assessment.profit_calculator.enums.ShipmentStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "shipment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Shipment extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ShipmentStatusEnum status = ShipmentStatusEnum.CREATED;

    @Column(nullable = false)
    private boolean active = true;

}
