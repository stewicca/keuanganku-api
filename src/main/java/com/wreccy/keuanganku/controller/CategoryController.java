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

    @GetMapping(path = "/{name}")
    public ResponseEntity<?> getCategoryByName(@PathVariable String name) {
        CategoryRequest request = CategoryRequest.builder()
                .name(name)
                .build();

        return ResponseUtil.buildResponse(
                HttpStatus.OK,
                "Category retrieved successful",
                categoryService.getByName(request)
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
