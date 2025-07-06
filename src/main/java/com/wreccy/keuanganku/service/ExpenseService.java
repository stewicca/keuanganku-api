package com.wreccy.keuanganku.service;

import com.wreccy.keuanganku.dto.*;

import java.util.List;

public interface ExpenseService {
    List<ExpenseResponse> getExpenses();

    ExpenseResponse addExpense(ExpenseRequest request);
}
