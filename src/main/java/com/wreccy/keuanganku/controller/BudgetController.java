package com.wreccy.keuanganku.controller;

import com.wreccy.keuanganku.dto.BudgetRequest;
import com.wreccy.keuanganku.service.BudgetService;
import com.wreccy.keuanganku.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/budgets")
public class BudgetController {
    private final BudgetService budgetService;

    @GetMapping
    public ResponseEntity<?> getBudgets(
            @RequestParam Integer year,
            @RequestParam Integer month
    ) {
        BudgetRequest request = BudgetRequest.builder()
                .year(year)
                .month(month)
                .build();

        return ResponseUtil.buildResponse(
                HttpStatus.OK,
                "Get budgets successful",
                budgetService.getAll(request)
        );
    }
}
