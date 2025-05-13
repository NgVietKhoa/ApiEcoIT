package com.example.ecoit2.repository.specs;

import com.example.ecoit2.entity.Intern;
import com.example.ecoit2.entity.InternWorkSchedule;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InternWSSpecification {

    public static Specification<InternWorkSchedule> filterInternWS(
            Integer internId,
            Integer availableHoursPerWeek,
            Date startDate,
            Date endDate,
            LocalDateTime createdAt,
            Integer createdBy,
            LocalDateTime updatedAt,
            Integer updatedBy) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (internId != null) {
                predicates.add(criteriaBuilder.equal(root.get("intern").get("id"), internId));
            }

            if (availableHoursPerWeek != null) {
                predicates.add(criteriaBuilder.equal(root.get("availableHoursPerWeek"), availableHoursPerWeek));
            }

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
                        ), startDate));
            }

            if (endDate != null) {
                predicates.add(criteriaBuilder.equal(
                        criteriaBuilder.function(
                                "DATE",
                                Date.class,
                                criteriaBuilder.function(
                                        "timezone",
                                        Date.class,
                                        criteriaBuilder.literal("UTC"),
                                        root.get("endDate")
                                )
                        ), endDate));
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
