package com.wreccy.keuanganku.service;

import com.wreccy.keuanganku.dto.*;
import com.wreccy.keuanganku.entity.Budget;

import java.util.List;

public interface BudgetService {
    List<BudgetResponse> getAll(BudgetRequest request);

    List<BudgetResponse> initialBudget(BudgetRequest request);

    Budget addSpentAmount(BudgetSpentAmountRequest request);
}
