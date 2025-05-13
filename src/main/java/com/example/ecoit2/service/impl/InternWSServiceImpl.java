package com.example.ecoit2.service.impl;

import com.example.ecoit2.dto.InternWSDTO;
import com.example.ecoit2.entity.Intern;
import com.example.ecoit2.entity.InternWorkSchedule;
import com.example.ecoit2.repository.InternRepo;
import com.example.ecoit2.repository.InternWSRepo;
import com.example.ecoit2.repository.specs.InternWSSpecification;
import com.example.ecoit2.service.InternWSService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class InternWSServiceImpl implements InternWSService {

    private final InternWSRepo internWSRepo;
    private final InternRepo internRepo;

    public InternWSServiceImpl(InternWSRepo internWSRepo, InternRepo internRepo) {
        this.internWSRepo = internWSRepo;
        this.internRepo = internRepo;
    }

    @Override
    public Iterable<InternWorkSchedule> getAllInternWS() {
        return internWSRepo.findAll();
    }

    @Override
    public Page<InternWorkSchedule> findWithPagination(Pageable pageable) {
        return internWSRepo.findAll(pageable);
    }

    @Override
    public InternWorkSchedule saveInternWS(InternWSDTO dto) {
        Intern intern = internRepo.findById(dto.getInternId())
                .orElseThrow(() -> new EntityNotFoundException("Intern với ID " + dto.getInternId() + " không tìm thấy"));

        InternWorkSchedule internWorkSchedule = new InternWorkSchedule();
        return getInternWorkSchedule(dto, intern, internWorkSchedule);
    }

    @Override
    public InternWorkSchedule updateInternWS(Integer id, InternWSDTO dto) {
        InternWorkSchedule existingInternWS = internWSRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("InternWS với ID " + id + " không tìm thấy"));

        Intern intern = internRepo.findById(dto.getInternId())
                .orElseThrow(() -> new EntityNotFoundException("Intern với ID " + dto.getInternId() + " không tìm thấy"));

        return getInternWorkSchedule(dto, intern, existingInternWS);
    }

    private InternWorkSchedule getInternWorkSchedule(InternWSDTO dto, Intern intern, InternWorkSchedule internWorkSchedule) {
        internWorkSchedule.setIntern(intern);
        internWorkSchedule.setAvailableHoursPerWeek(dto.getAvailableHoursPerWeek());
        internWorkSchedule.setStartDate(dto.getStartDate());
        internWorkSchedule.setEndDate(dto.getEndDate());
        internWorkSchedule.setCreatedAt(dto.getCreatedAt() != null ? dto.getCreatedAt() : LocalDateTime.now());
        internWorkSchedule.setCreatedBy(dto.getCreatedBy() != null ? dto.getCreatedBy() : 1);
        internWorkSchedule.setUpdatedAt(dto.getUpdatedAt() != null ? dto.getUpdatedAt() : LocalDateTime.now());
        internWorkSchedule.setUpdatedBy(dto.getUpdatedBy() != null ? dto.getUpdatedBy() : 1);

        return internWSRepo.save(internWorkSchedule);
    }

    @Override
    public void deleteInternWS(Integer id) {
        internWSRepo.deleteById(id);
    }

    @Override
    public Page<InternWorkSchedule> searchAndFilter(Integer internId, Integer availableHoursPerWeek, Date startDate, Date endDate, LocalDateTime createdAt, Integer createdBy, LocalDateTime updatedAt, Integer updatedBy, Pageable pageable) {
        return internWSRepo.findAll(
                InternWSSpecification.filterInternWS(
                        internId, availableHoursPerWeek, startDate, endDate,
                        createdAt, createdBy, updatedAt, updatedBy),
                pageable);
    }
}