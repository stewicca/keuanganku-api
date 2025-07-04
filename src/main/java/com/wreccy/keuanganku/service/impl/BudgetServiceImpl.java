package com.wreccy.keuanganku.service.impl;

import com.wreccy.keuanganku.dto.*;
import com.wreccy.keuanganku.entity.Budget;
import com.wreccy.keuanganku.repository.BudgetRepository;
import com.wreccy.keuanganku.service.BudgetService;
import com.wreccy.keuanganku.specification.BudgetSpecification;
import com.wreccy.keuanganku.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BudgetServiceImpl implements BudgetService {
    private final BudgetRepository budgetRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<BudgetResponse> getAll(BudgetRequest request) {
        Specification<Budget> specification = BudgetSpecification.getSpecification(request);
        List<Budget> budgets = budgetRepository.findAll(specification);

        if (budgets.isEmpty()) {
            return initialBudget(request).stream().map(MapperUtil::toBudgetResponse).collect(Collectors.toList());
        }

        return budgets.stream().map(MapperUtil::toBudgetResponse).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<Budget> initialBudget(BudgetRequest request) {
        List<Budget> budgets = new ArrayList<>();



        return List.of();
    }
}
