package com.dachser.assessment.profit_calculator.repository;

import com.dachser.assessment.profit_calculator.model.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
}
