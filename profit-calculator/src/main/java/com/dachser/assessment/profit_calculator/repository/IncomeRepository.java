package com.dachser.assessment.profit_calculator.repository;

import com.dachser.assessment.profit_calculator.model.Cost;
import com.dachser.assessment.profit_calculator.model.Income;
import com.dachser.assessment.profit_calculator.model.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {

    List<Income> findByShipmentId(Long shipmentId);

    List<Income> findAllByShipmentAndActiveTrue(Shipment shipment);
}
