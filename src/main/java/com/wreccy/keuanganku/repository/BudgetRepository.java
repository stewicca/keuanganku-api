package com.wreccy.keuanganku.repository;

import com.wreccy.keuanganku.entity.Budget;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, String> {
    List<Budget> findAll(Specification<Budget> specification);
}
