package com.example.ecoit2.controller;

import com.example.ecoit2.dto.InternPRDTO;
import com.example.ecoit2.entity.Intern;
import com.example.ecoit2.entity.InternPerformanceReview;
import com.example.ecoit2.entity.InternWorkSchedule;
import com.example.ecoit2.service.InternPRService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;

@RestController
@RequestMapping("/api/internPR")
@RequiredArgsConstructor
public class InternPRController {

    private final InternPRService internPRService;

    @GetMapping
    public Iterable<InternPerformanceReview> getAllInternPR() {
       return internPRService.getAllInternPR();
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<InternPerformanceReview>> getInternsWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id,desc") String sort) {
        String[] sortParams = sort.split(",");
        Sort sortOrder = Sort.by(Sort.Direction.fromString(sortParams[1]), sortParams[0]);
        Pageable pageable = PageRequest.of(page, size, sortOrder);
        return ResponseEntity.ok(internPRService.findWithPagination(pageable));
    }

    @PostMapping("/add")
    public InternPerformanceReview addInternPR(@RequestBody InternPRDTO internPRDTO) {
        return internPRService.addInternPR(internPRDTO);
    }

    @PutMapping("/{id}")
    public InternPerformanceReview updateInternPR(@PathVariable Integer id, @RequestBody InternPRDTO internPRDTO){
        return internPRService.updateInternPR(id,internPRDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteInternPR(@PathVariable Integer id){
        internPRService.deleleInternPR(id);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<InternPerformanceReview>> searchAndFilter(
            @RequestParam(required = false) Integer internId,
            @RequestParam(required = false) Integer performanceScore,
            @RequestParam(required = false) Date reviewDate,
            @RequestParam(required = false) Integer reviewerId,
            @RequestParam(required = false) String comments,
            @RequestParam(required = false) LocalDateTime createdAt,
            @RequestParam(required = false) Integer createdBy,
            @RequestParam(required = false) LocalDateTime updatedAt,
            @RequestParam(required = false) Integer updatedBy,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id,desc") String sort) {
        String[] sortParams = sort.split(",");
        Sort sortOrder = Sort.by(Sort.Direction.fromString(sortParams[1]), sortParams[0]);
        Pageable pageable = PageRequest.of(page, size, sortOrder);
        Page<InternPerformanceReview> result = internPRService.searchAndFilter(
                internId, performanceScore, reviewDate, reviewerId, comments,
                createdAt, createdBy, updatedAt, updatedBy, pageable);
        return ResponseEntity.ok(result);
    }

}
