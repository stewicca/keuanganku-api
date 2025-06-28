package com.wreccy.keuanganku.repository;

import com.wreccy.keuanganku.entity.Category;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
}
