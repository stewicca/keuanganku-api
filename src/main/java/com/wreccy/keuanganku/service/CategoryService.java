package com.wreccy.keuanganku.service;

import com.wreccy.keuanganku.dto.*;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> getAll();

    CategoryResponse getByName(CategoryRequest request);

    CategoryResponse create(CategoryRequest request);
}
