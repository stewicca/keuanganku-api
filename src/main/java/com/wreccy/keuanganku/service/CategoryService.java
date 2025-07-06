package com.wreccy.keuanganku.service;

import com.wreccy.keuanganku.dto.*;
import com.wreccy.keuanganku.entity.Category;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> getAll();

    Category getByName(String name);

    CategoryResponse create(CategoryRequest request);
}
