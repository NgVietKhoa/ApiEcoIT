package com.example.ecoit2.service;

import com.example.ecoit2.entity.Intern;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public interface InternService {
    Iterable<Intern> getAllIntern();
    Page<Intern> findWithPagination(Pageable pageable);
    Intern saveIntern(Intern intern);
    Intern updateIntern(Integer id, Intern intern);
    void deleteIntern(Integer id);
    Intern findInternById(Integer id);
    Page<Intern> searchAndFilter(
            String name,
            Date dateOfBirth,
            Integer gender,
            Date startDate,
            Date nextReviewDate,
            LocalDateTime createdAt,
            Integer createdBy,
            LocalDateTime updatedAt,
            Integer updatedBy,
            Pageable pageable);
}