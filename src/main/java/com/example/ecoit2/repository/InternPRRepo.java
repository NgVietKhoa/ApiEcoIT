package com.example.ecoit2.repository;

import com.example.ecoit2.entity.InternPerformanceReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface InternPRRepo extends JpaRepository<InternPerformanceReview, Integer>, JpaSpecificationExecutor<InternPerformanceReview> {
}
