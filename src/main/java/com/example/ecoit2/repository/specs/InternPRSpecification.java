package com.example.ecoit2.repository.specs;

import com.example.ecoit2.entity.Intern;
import com.example.ecoit2.entity.InternPerformanceReview;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InternPRSpecification {

    public static Specification<InternPerformanceReview> filterInternPR(
            Integer internId,
            Integer performanceScore,
            Date reviewDate,
            Integer reviewerId,
            String comments,
            LocalDateTime createdAt,
            Integer createdBy,
            LocalDateTime updatedAt,
            Integer updatedBy) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (internId != null) {
                predicates.add(criteriaBuilder.equal(root.get("intern").get("id"), internId));
            }

            if (performanceScore != null) {
                predicates.add(criteriaBuilder.equal(root.get("performanceScore"), performanceScore));
            }

            if (reviewDate != null) {
                predicates.add(criteriaBuilder.equal(root.get("reviewDate"), reviewDate));
            }

            if (reviewerId != null) {
                predicates.add(criteriaBuilder.equal(root.get("reviewerId"), reviewerId));
            }

            if (comments != null) {
                predicates.add(criteriaBuilder.equal(root.get("comments"), comments));
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
