package com.example.ecoit2.service;

import com.example.ecoit2.dto.InternWSDTO;
import com.example.ecoit2.entity.Intern;
import com.example.ecoit2.entity.InternWorkSchedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public interface InternWSService {

    Iterable<InternWorkSchedule> getAllInternWS();
    Page<InternWorkSchedule> findWithPagination(Pageable pageable);
    InternWorkSchedule saveInternWS(InternWSDTO internWSDTO);
    InternWorkSchedule updateInternWS(Integer id, InternWSDTO InternWSDTO);
    void deleteInternWS(Integer id);

    Page<InternWorkSchedule> searchAndFilter(
            Integer internId,
            Integer availableHoursPerWeek,
            Date startDate,
            Date endDate,
            LocalDateTime createdAt,
            Integer createdBy,
            LocalDateTime updatedAt,
            Integer updatedBy,
            Pageable pageable);

}
