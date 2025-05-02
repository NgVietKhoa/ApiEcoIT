package com.example.ecoit2.service.impl;

import com.example.ecoit2.entity.Intern;
import com.example.ecoit2.repository.InternRepo;
import com.example.ecoit2.service.InternService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class InternServiceImpl implements InternService {

    private final InternRepo internRepo;

    public InternServiceImpl(InternRepo internRepo) {
        this.internRepo = internRepo;
    }

    @Override
    public Iterable<Intern> getAllIntern() {
        return internRepo.findAll();
    }

    @Override
    public Page<Intern> findWithPagination(Pageable pageable) {
        return internRepo.findAll(pageable);
    }

    @Override
    public Intern saveIntern(Intern intern) {
        return internRepo.save(intern);
    }

    @Override
    public Intern updateIntern(Integer id, Intern intern) {
        Intern existingIntern = internRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Thực tập sinh với ID " + id + " không tìm thấy"));

        existingIntern.setName(intern.getName());
        existingIntern.setDateOfBirth(intern.getDateOfBirth());
        existingIntern.setGender(intern.getGender());
        existingIntern.setStartDate(intern.getStartDate());
        existingIntern.setNextReviewDate(intern.getNextReviewDate());
        existingIntern.setCreatedAt(intern.getCreatedAt() != null ? intern.getCreatedAt() : LocalDateTime.now());
        existingIntern.setCreatedBy(intern.getCreatedBy() != null ? intern.getCreatedBy() : 1);
        existingIntern.setUpdatedAt(intern.getUpdatedAt() != null ? intern.getUpdatedAt() : LocalDateTime.now());
        existingIntern.setUpdatedBy(intern.getUpdatedBy() != null ? intern.getUpdatedBy() : 1);

        return internRepo.save(existingIntern);
    }

    @Override
    public void deleteIntern(Integer id) {
        if (!internRepo.existsById(id)) {
            throw new EntityNotFoundException("Thực tập sinh với ID " + id + " không tìm thấy");
        }
        internRepo.deleteById(id);
    }

    @Override
    public Intern findInternById(Integer id) {
        return internRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Thực tập sinh với ID " + id + " không tìm thấy"));
    }
}