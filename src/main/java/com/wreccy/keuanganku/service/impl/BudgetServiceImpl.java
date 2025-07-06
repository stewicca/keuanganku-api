package com.wreccy.keuanganku.service.impl;

import com.wreccy.keuanganku.dto.*;
import com.wreccy.keuanganku.util.*;
import com.wreccy.keuanganku.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.wreccy.keuanganku.service.BudgetService;
import org.springframework.security.core.Authentication;
import org.springframework.data.jpa.domain.Specification;
import com.wreccy.keuanganku.repository.BudgetRepository;
import org.springframework.web.server.ResponseStatusException;
import com.wreccy.keuanganku.specification.BudgetSpecification;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BudgetServiceImpl implements BudgetService {
    private final ValidationUtil validationUtil;
    private final BudgetRepository budgetRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<BudgetResponse> getAll(BudgetRequest request) {
        Specification<Budget> specification = BudgetSpecification.getSpecification(request);
        List<Budget> budgets = budgetRepository.findAll(specification);

        if (budgets.isEmpty()) {
            return initialBudget(request);
        }

        return budgets.stream().map(MapperUtil::toBudgetResponse).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<BudgetResponse> initialBudget(BudgetRequest request) {
        List<Budget> budgets = new ArrayList<>();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = (User) authentication.getPrincipal();

        if (user.getMonthlySalary() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No monthly salary");
        }

        budgets.add(Budget.builder()
                        .name("NEEDS")
                        .year(request.getYear())
                        .month(request.getMonth())
                        .budgetAmount((int) (0.5F * user.getMonthlySalary()))
                        .spentAmount(0)
                        .percentage(0.5F)
                        .user(user)
                .build());

        budgets.add(Budget.builder()
                        .name("WANTS")
                        .year(request.getYear())
                        .month(request.getMonth())
                        .budgetAmount((int) (0.3F * user.getMonthlySalary()))
                        .spentAmount(0)
                        .percentage(0.3F)
                        .user(user)
                .build());

        budgets.add(Budget.builder()
                        .name("SAVINGS")
                        .year(request.getYear())
                        .month(request.getMonth())
                        .budgetAmount((int) (0.2F * user.getMonthlySalary()))
                        .spentAmount(0)
                        .percentage(0.2F)
                        .user(user)
                .build());

        budgetRepository.saveAll(budgets);

        return budgets.stream().map(MapperUtil::toBudgetResponse).collect(Collectors.toList());
    }

    @Override
    public Budget addSpentAmount(BudgetSpentAmountRequest request) {
        validationUtil.validate(request);

        Specification<Budget> specification = BudgetSpecification.getSpentAmountSpecification(request);

        Budget budget = budgetRepository.findOne(specification)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Budget not found"));

        if (budget.getBudgetAmount() < budget.getSpentAmount() + request.getSpentAmount()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Spent amount is less than spend amount");
        }

        budget.setSpentAmount(budget.getSpentAmount() + request.getSpentAmount());

        return budgetRepository.save(budget);
    }
}
