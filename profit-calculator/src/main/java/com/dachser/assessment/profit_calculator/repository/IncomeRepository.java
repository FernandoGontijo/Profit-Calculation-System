package com.dachser.assessment.profit_calculator.repository;

import com.dachser.assessment.profit_calculator.entity.Cost;
import com.dachser.assessment.profit_calculator.entity.Income;
import com.dachser.assessment.profit_calculator.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {

    List<Income> findAllByShipment_IdAndActiveTrue(Long shipment);

    List<Income> findAllByActiveTrue();
}
