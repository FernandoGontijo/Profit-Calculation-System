package com.dachser.assessment.profit_calculator.repository;

import com.dachser.assessment.profit_calculator.entity.Shipment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {

    List<Shipment> findAllByActiveTrue();

    List<Shipment> findByStatus(String status);

    Optional<Shipment> findByIdAndActiveTrue(Long id);
}
