package com.example.ecoit2.service;

import com.example.ecoit2.dto.InternWSDTO;
import com.example.ecoit2.entity.InternWorkSchedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface InternWSService {

    Iterable<InternWorkSchedule> getAllInternWS();
    Page<InternWorkSchedule> findWithPagination(Pageable pageable);
    InternWorkSchedule saveInternWS(InternWSDTO internWSDTO);
    InternWorkSchedule updateInternWS(Integer id, InternWSDTO InternWSDTO);
    void deleteInternWS(Integer id);
}
