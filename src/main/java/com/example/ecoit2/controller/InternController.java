package com.example.ecoit2.controller;

import com.example.ecoit2.entity.Intern;
import com.example.ecoit2.service.InternService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
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
}