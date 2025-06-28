package com.wreccy.keuanganku.repository;

import com.wreccy.keuanganku.entity.Budget;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, String> {
}
