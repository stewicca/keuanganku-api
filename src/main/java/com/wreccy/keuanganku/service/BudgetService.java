package com.wreccy.keuanganku.service;

import com.wreccy.keuanganku.dto.*;
import com.wreccy.keuanganku.entity.Budget;

import java.util.List;

public interface BudgetService {
    List<BudgetResponse> getAll(BudgetRequest request);

    List<Budget> initialBudget(BudgetRequest request);
}
