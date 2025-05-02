package com.example.ecoit2.service.impl;

import com.example.ecoit2.dto.InternPRDTO;
import com.example.ecoit2.entity.Intern;
import com.example.ecoit2.entity.InternPerformanceReview;
import com.example.ecoit2.repository.InternPRRepo;
import com.example.ecoit2.repository.InternRepo;
import com.example.ecoit2.service.InternPRService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class InternPRServiceImpl implements InternPRService {

    private final InternPRRepo internPRRepo;

    private final InternRepo internRepo;

    public InternPRServiceImpl(InternPRRepo internPRRepo, InternRepo internRepo) {
        this.internPRRepo = internPRRepo;
        this.internRepo = internRepo;
    }

    @Override
    public Iterable<InternPerformanceReview> getAllInternPR() {
        return internPRRepo.findAll();
    }

    @Override
    public Page<InternPerformanceReview> findWithPagination(Pageable pageable) {
        return internPRRepo.findAll(pageable);
    }

    @Override
    public InternPerformanceReview addInternPR(InternPRDTO internPRDTO) {
        Intern intern = internRepo.findById(internPRDTO.getInternId())
                .orElseThrow(() -> new EntityNotFoundException("Intern với ID " + internPRDTO.getInternId() + " không tìm thấy"));

        InternPerformanceReview internPerformanceReview = new InternPerformanceReview();
        return getInternPerformanceReview(internPRDTO, intern, internPerformanceReview);
    }

    @Override
    public InternPerformanceReview updateInternPR(Integer id, InternPRDTO internPRDTO) {
        InternPerformanceReview exitstingInternPR = internPRRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("InternWS với ID " + id + " không tìm thấy"));

        Intern intern = internRepo.findById(internPRDTO.getInternId())
                .orElseThrow(() -> new EntityNotFoundException("Intern với ID " + internPRDTO.getInternId() + " không tìm thấy"));


        return getInternPerformanceReview(internPRDTO, intern, exitstingInternPR);
    }

    private InternPerformanceReview getInternPerformanceReview(InternPRDTO internPRDTO, Intern intern, InternPerformanceReview internPerformanceReview) {
        internPerformanceReview.setIntern(intern);
        internPerformanceReview.setPerformanceScore(internPRDTO.getPerformanceScore());
        internPerformanceReview.setReviewDate(internPRDTO.getReviewDate());
        internPerformanceReview.setReviewerId(internPRDTO.getReviewerId());
        internPerformanceReview.setComments(internPRDTO.getComments());
        internPerformanceReview.setCreatedAt(internPRDTO.getCreatedAt() != null ? internPRDTO.getCreatedAt() : LocalDateTime.now());
        internPerformanceReview.setCreatedBy(internPRDTO.getCreatedBy() != null ? internPRDTO.getCreatedBy() : 1);
        internPerformanceReview.setUpdatedAt(internPRDTO.getUpdatedAt() != null ? internPRDTO.getUpdatedAt() : LocalDateTime.now());
        internPerformanceReview.setUpdatedBy(internPRDTO.getUpdatedBy() != null ? internPRDTO.getUpdatedBy() : 1);

        return internPRRepo.save(internPerformanceReview);
    }

    @Override
    public void deleleInternPR(Integer id) {
        internPRRepo.deleteById(id);
    }
}
