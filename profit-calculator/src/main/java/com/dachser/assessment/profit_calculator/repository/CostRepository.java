package com.dachser.assessment.profit_calculator.repository;

import com.dachser.assessment.profit_calculator.entity.Cost;
import com.dachser.assessment.profit_calculator.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CostRepository extends JpaRepository<Cost, Long> {

    List<Cost> findByShipment_IdAndActiveTrue(Long shipmentId);

    List<Cost> findAllByShipment_IdAndActiveTrue(Long shipmentId);

    List<Cost> findAllByActiveTrue();
}
