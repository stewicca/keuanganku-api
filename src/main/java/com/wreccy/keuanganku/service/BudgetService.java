package com.wreccy.keuanganku.service;

import com.wreccy.keuanganku.dto.*;
import com.wreccy.keuanganku.entity.*;

import java.util.List;

public interface BudgetService {
    List<BudgetResponse> getAll(BudgetRequest request);

    List<BudgetResponse> initialBudget(BudgetRequest request);

    void addSpentAmount(BudgetSpentAmountRequest request, User user);
}
