package com.example.ecoit2.repository.specs;

import com.example.ecoit2.entity.Product;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductSpecification {

    public static Specification<Product> filterProducts(
            String productName,
            String productType,
            String description,
            Date startDate,
            String status,
            LocalDateTime createdAt,
            Integer createdBy,
            LocalDateTime updatedAt,
            Integer updatedBy) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (productName != null && !productName.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("productName"), "%" + productName + "%"));
            }

            if (productType != null && !productType.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("productType"), "%" + productType + "%"));
            }

            if (description != null && !description.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("description"), "%" + description + "%"));
            }

            if (startDate != null) {
                predicates.add(criteriaBuilder.equal(
                        criteriaBuilder.function("DATE", Date.class, root.get("startDate")),
                        startDate
                ));
            }

            if (status != null && !status.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("status"), "%" + status + "%"));
            }

            if (createdAt != null) {
                predicates.add(criteriaBuilder.equal(root.get("createdAt"), createdAt));
            }

            if (createdBy != null) {
                predicates.add(criteriaBuilder.equal(root.get("createdBy"), createdBy));
            }

            if (updatedAt != null) {
                predicates.add(criteriaBuilder.equal(root.get("updatedAt"), updatedAt));
            }

            if (updatedBy != null) {
                predicates.add(criteriaBuilder.equal(root.get("updatedBy"), updatedBy));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
