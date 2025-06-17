package com.dachser.assessment.profit_calculator.repository;

import com.dachser.assessment.profit_calculator.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {
}
