package com.example.ecoit2.controller;

import com.example.ecoit2.dto.InternWSDTO;
import com.example.ecoit2.entity.InternWorkSchedule;
import com.example.ecoit2.service.InternWSService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
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
}
