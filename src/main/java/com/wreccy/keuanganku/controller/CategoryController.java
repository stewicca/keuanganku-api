package com.wreccy.keuanganku.controller;

import org.springframework.http.*;
import lombok.RequiredArgsConstructor;
import com.wreccy.keuanganku.util.ResponseUtil;
import com.wreccy.keuanganku.dto.CategoryRequest;
import org.springframework.web.bind.annotation.*;
import com.wreccy.keuanganku.service.CategoryService;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> getCategories() {
        return ResponseUtil.buildResponse(
                HttpStatus.OK,
                "Categories retrieved successful",
                categoryService.getAll()
        );
    }

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody CategoryRequest request) {
        return ResponseUtil.buildResponse(
                HttpStatus.CREATED,
                "Create category successful",
                categoryService.create(request)
        );
    }
}
