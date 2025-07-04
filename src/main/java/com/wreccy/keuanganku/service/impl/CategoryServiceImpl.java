package com.wreccy.keuanganku.service.impl;

import com.wreccy.keuanganku.dto.*;
import com.wreccy.keuanganku.util.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import com.wreccy.keuanganku.entity.Category;
import org.springframework.stereotype.Service;
import com.wreccy.keuanganku.service.CategoryService;
import com.wreccy.keuanganku.repository.CategoryRepository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    private final ValidationUtil validationUtil;
    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryResponse> getAll() {
        List<Category> categories = categoryRepository.findAll();

        return categories.stream().map(MapperUtil::toCategoryResponse).collect(Collectors.toList());
    }

    @Override
    public CategoryResponse getByName(CategoryRequest request) {
        validationUtil.validate(request);

        return MapperUtil.toCategoryResponse(categoryRepository.findByName(request.getName()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found")));
    }

    @Override
    public CategoryResponse create(CategoryRequest request) {
        validationUtil.validate(request);

        Category category = Category.builder()
                .name(request.getName().toUpperCase())
                .build();

        return MapperUtil.toCategoryResponse(categoryRepository.save(category));
    }
}
