package com.wreccy.keuanganku.util;

import com.wreccy.keuanganku.dto.*;
import com.wreccy.keuanganku.entity.*;

public class MapperUtil {
    public static UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .monthly_salary(user.getMonthlySalary())
                .region_umr(user.getRegionUmr())
                .build();
    }

    public static AuthResponse toAuthResponse(String token) {
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public static CategoryResponse toCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .label(category.getName().substring(0, 1).toUpperCase() + category.getName().substring(1).toLowerCase())
                .build();
    }

    public static BudgetResponse toBudgetResponse(Budget budget) {
        return BudgetResponse.builder()
                .id(budget.getId())
                .name(budget.getName())
                .label(budget.getName().substring(0, 1).toUpperCase() + budget.getName().substring(1))
                .year(budget.getYear())
                .month(budget.getMonth())
                .budget_amount(budget.getBudgetAmount())
                .current_amount(budget.getBudgetAmount() - budget.getSpentAmount())
                .percentage(budget.getPercentage())
                .build();
    }
}
