package com.dachser.assessment.profit_calculator.repository;

import com.dachser.assessment.profit_calculator.model.Cost;
import com.dachser.assessment.profit_calculator.model.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CostRepository extends JpaRepository<Cost, Long> {

    List<Cost> findByShipmentId(Long shipmentId);

    List<Cost> findAllByShipmentAndActiveTrue(Shipment shipment);
}
