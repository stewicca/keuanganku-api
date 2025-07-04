package com.wreccy.keuanganku.specification;

import com.wreccy.keuanganku.entity.Budget;
import jakarta.persistence.criteria.Predicate;
import com.wreccy.keuanganku.dto.BudgetRequest;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class BudgetSpecification {
    public static Specification<Budget> getSpecification(BudgetRequest request) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (request.getYear() != null) {
                predicates.add(criteriaBuilder.equal(root.get("year").as(Integer.class), request.getYear()));
            }

            if (request.getMonth() != null) {
                predicates.add(criteriaBuilder.equal(root.get("month").as(Integer.class), request.getMonth()));
            }

            if (predicates.isEmpty()) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
        };
    }
}
