package com.example.ecoit2.controller;

import com.example.ecoit2.entity.Intern;
import com.example.ecoit2.service.InternService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;

@RestController
@RequestMapping("/api/intern")
@RequiredArgsConstructor
public class InternController {

    private final InternService internService;

    @GetMapping
    public Iterable<Intern> getAllInterns() {
        return internService.getAllIntern();
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<Intern>> getInternsWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id,desc") String sort) {
        String[] sortParams = sort.split(",");
        Sort sortOrder = Sort.by(Sort.Direction.fromString(sortParams[1]), sortParams[0]);
        Pageable pageable = PageRequest.of(page, size, sortOrder);
        return ResponseEntity.ok(internService.findWithPagination(pageable));
    }

    @PostMapping("/add")
    public void addIntern(@RequestBody Intern intern) {
        internService.saveIntern(intern);
    }

    @DeleteMapping("/{id}")
    public void deleted(@PathVariable Integer id) {
        internService.deleteIntern(id);
    }

    @PutMapping("/{id}")
    public void updateIntern(@PathVariable Integer id, @RequestBody Intern intern) {
        internService.updateIntern(id, intern);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Intern>> searchAndFilter(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateOfBirth,
            @RequestParam(required = false) Integer gender,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date nextReviewDate,
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
        Page<Intern> result = internService.searchAndFilter(
                name, dateOfBirth, gender, startDate, nextReviewDate,
                createdAt, createdBy, updatedAt, updatedBy, pageable);
        return ResponseEntity.ok(result);
    }
}