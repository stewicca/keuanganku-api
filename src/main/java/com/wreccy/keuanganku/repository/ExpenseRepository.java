package com.wreccy.keuanganku.repository;

import com.wreccy.keuanganku.entity.Expense;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, String> {
}
