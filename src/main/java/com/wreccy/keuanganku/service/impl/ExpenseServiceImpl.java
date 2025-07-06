package com.wreccy.keuanganku.service.impl;

import com.wreccy.keuanganku.dto.*;
import com.wreccy.keuanganku.util.*;
import com.wreccy.keuanganku.entity.*;
import lombok.RequiredArgsConstructor;
import com.wreccy.keuanganku.service.*;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import com.wreccy.keuanganku.repository.ExpenseRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.time.LocalDate;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ExpenseServiceImpl implements ExpenseService {
    private final BudgetService budgetService;
    private final ValidationUtil validationUtil;
    private final CategoryService categoryService;
    private final ExpenseRepository expenseRepository;

    @Override
    public List<ExpenseResponse> getExpenses() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = (User) authentication.getPrincipal();

        List<Expense> expenses = expenseRepository.findAllByUserId(user.getId());

        return expenses.stream().map(MapperUtil::toExpenseResponse).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ExpenseResponse addExpense(ExpenseRequest request) {
        validationUtil.validate(request);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = (User) authentication.getPrincipal();

        Category category = categoryService.getByName(request.getCategory().toLowerCase());

        Expense expense = Expense.builder()
                .amount(request.getAmount())
                .description(request.getDescription())
                .expenseDate(LocalDate.now())
                .category(category)
                .user(user)
                .build();

        BudgetSpentAmountRequest budgetSpentAmountRequest = BudgetSpentAmountRequest.builder()
                .name("NEEDS")
                .year(expense.getExpenseDate().getYear())
                .month(expense.getExpenseDate().getMonthValue())
                .spentAmount(expense.getAmount())
                .build();

        budgetService.addSpentAmount(budgetSpentAmountRequest, user);

        return MapperUtil.toExpenseResponse(expenseRepository.save(expense));
    }
}
