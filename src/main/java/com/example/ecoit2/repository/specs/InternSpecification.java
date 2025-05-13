package com.example.ecoit2.repository.specs;

import com.example.ecoit2.entity.Intern;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InternSpecification {

    public static Specification<Intern> filterInterns(
            String name,
            Date dateOfBirth,
            Integer gender,
            Date startDate,
            Date nextReviewDate,
            LocalDateTime createdAt,
            Integer createdBy,
            LocalDateTime updatedAt,
            Integer updatedBy) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Lọc theo name
            if (name != null && !name.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
            }

            // Lọc theo dateOfBirth
            if (dateOfBirth != null) {
                predicates.add(criteriaBuilder.equal(
                        criteriaBuilder.function(
                                "DATE",
                                Date.class,
                                criteriaBuilder.function(
                                        "timezone",
                                        Date.class,
                                        criteriaBuilder.literal("UTC"),
                                        root.get("dateOfBirth")
                                )
                        ),
                        dateOfBirth
                ));
            }

            // Lọc theo gender
            if (gender != null) {
                predicates.add(criteriaBuilder.equal(root.get("gender"), gender));
            }

            // Lọc theo startDate
            if (startDate != null) {
                predicates.add(criteriaBuilder.equal(
                        criteriaBuilder.function(
                                "DATE",
                                Date.class,
                                criteriaBuilder.function(
                                        "timezone",
                                        Date.class,
                                        criteriaBuilder.literal("UTC"),
                                        root.get("startDate")
                                )
                        ),
                        startDate));
            }

            // Lọc theo nextReviewDate
            if (nextReviewDate != null) {
                predicates.add(criteriaBuilder.equal(
                        criteriaBuilder.function(
                                "DATE",
                                Date.class,
                                criteriaBuilder.function(
                                        "timezone",
                                        Date.class,
                                        criteriaBuilder.literal("UTC"),
                                        root.get("nextReviewDate")
                                )
                        ),
                        nextReviewDate));
            }

            // Lọc theo createdAt
            if (createdAt != null) {
                predicates.add(criteriaBuilder.equal(root.get("createdAt"), createdAt));
            }

            // Lọc theo createdBy
            if (createdBy != null) {
                predicates.add(criteriaBuilder.equal(root.get("createdBy"), createdBy));
            }

            // Lọc theo updatedAt
            if (updatedAt != null) {
                predicates.add(criteriaBuilder.equal(root.get("updatedAt"), updatedAt));
            }

            // Lọc theo updatedBy
            if (updatedBy != null) {
                predicates.add(criteriaBuilder.equal(root.get("updatedBy"), updatedBy));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
