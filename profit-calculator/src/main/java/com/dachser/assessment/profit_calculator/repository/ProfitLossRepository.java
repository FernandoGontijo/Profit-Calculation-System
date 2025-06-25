package com.dachser.assessment.profit_calculator.repository;

import com.dachser.assessment.profit_calculator.entity.ProfitLoss;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfitLossRepository extends JpaRepository<ProfitLoss, Long> {

    Page<ProfitLoss> findAllByActiveTrue(Pageable pageable);

    Optional<ProfitLoss> findByShipment_IdAndActiveTrue(Long shipmentId);

}
