package com.dachser.assessment.profit_calculator.repository;

import com.dachser.assessment.profit_calculator.entity.ProfitLoss;
import com.dachser.assessment.profit_calculator.entity.Shipment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfitLossRepository extends JpaRepository<ProfitLoss, Long> {
}
