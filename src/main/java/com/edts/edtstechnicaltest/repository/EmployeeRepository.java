package com.edts.edtstechnicaltest.repository;

import com.edts.edtstechnicaltest.model.entity.Employees;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employees, Long> {
    Page<Employees> findAll(Pageable pageable);
}
