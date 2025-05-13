package com.example.ecoit2.service;

import com.example.ecoit2.dto.InternPRDTO;
import com.example.ecoit2.entity.Intern;
import com.example.ecoit2.entity.InternPerformanceReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public interface InternPRService {

    Iterable<InternPerformanceReview> getAllInternPR();

    Page<InternPerformanceReview> findWithPagination(Pageable pageable);

    InternPerformanceReview addInternPR(InternPRDTO internPRDTO);

    InternPerformanceReview updateInternPR(Integer id, InternPRDTO internPRDTO);

    void deleleInternPR(Integer id);

    Page<InternPerformanceReview> searchAndFilter(
            Integer internId,
            Integer performanceScore,
            Date reviewDate,
            Integer reviewerId,
            String comments,
            LocalDateTime createdAt,
            Integer createdBy,
            LocalDateTime updatedAt,
            Integer updatedBy,
            Pageable pageable);
}

