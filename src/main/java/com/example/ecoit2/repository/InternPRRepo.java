package com.example.ecoit2.repository;

import com.example.ecoit2.entity.InternPerformanceReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InternPRRepo extends JpaRepository<InternPerformanceReview, Integer> {
}
