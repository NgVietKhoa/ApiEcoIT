package com.example.ecoit2.controller;

import com.example.ecoit2.dto.InternWSDTO;
import com.example.ecoit2.entity.Intern;
import com.example.ecoit2.entity.InternWorkSchedule;
import com.example.ecoit2.service.InternWSService;
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
@RequestMapping("/api/internWS")
@RequiredArgsConstructor
public class InternWSController {

    private final InternWSService internWSService;

    @GetMapping
    public Iterable<InternWorkSchedule> getAllInternWS(){
        return internWSService.getAllInternWS();
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<InternWorkSchedule>> getInternsWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id,desc") String sort) {
        String[] sortParams = sort.split(",");
        Sort sortOrder = Sort.by(Sort.Direction.fromString(sortParams[1]), sortParams[0]);
        Pageable pageable = PageRequest.of(page, size, sortOrder);
        return ResponseEntity.ok(internWSService.findWithPagination(pageable));
    }

    @PostMapping("/add")
    public InternWorkSchedule addInternWS(@RequestBody InternWSDTO internWSDTO) {
        return internWSService.saveInternWS(internWSDTO);
    }

    @PutMapping("/{id}")
    public InternWorkSchedule updateInternWS(@PathVariable Integer id, @RequestBody InternWSDTO internWSDTO){
        return internWSService.updateInternWS(id,internWSDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteInternWS(@PathVariable Integer id){
        internWSService.deleteInternWS(id);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<InternWorkSchedule>> searchAndFilter(
            @RequestParam(required = false) Integer internId,
            @RequestParam(required = false) Integer availableHoursPerWeek,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate,
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
        Page<InternWorkSchedule> result = internWSService.searchAndFilter(
                internId, availableHoursPerWeek, startDate, endDate,
                createdAt, createdBy, updatedAt, updatedBy, pageable);
        return ResponseEntity.ok(result);
    }
}
