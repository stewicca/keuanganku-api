package com.wreccy.keuanganku.controller;

import org.springframework.http.*;
import lombok.RequiredArgsConstructor;
import com.wreccy.keuanganku.util.ResponseUtil;
import com.wreccy.keuanganku.dto.ExpenseRequest;
import org.springframework.web.bind.annotation.*;
import com.wreccy.keuanganku.service.ExpenseService;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/expenses")
public class ExpenseController {
    private final ExpenseService expenseService;

    @GetMapping
    public ResponseEntity<?> getExpenses() {
        return ResponseUtil.buildResponse(
                HttpStatus.OK,
                "Get expenses successful",
                expenseService.getExpenses()
        );
    }

    @PostMapping
    public ResponseEntity<?> addExpense(@RequestBody ExpenseRequest request) {
        return ResponseUtil.buildResponse(
                HttpStatus.CREATED,
                "Add expense successful",
                expenseService.addExpense(request)
        );
    }
}
