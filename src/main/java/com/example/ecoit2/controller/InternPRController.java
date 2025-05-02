package com.example.ecoit2.controller;

import com.example.ecoit2.dto.InternPRDTO;
import com.example.ecoit2.entity.InternPerformanceReview;
import com.example.ecoit2.service.InternPRService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
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

}
