package com.example.ecoit2.repository;

import com.example.ecoit2.entity.InternWorkSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface InternWSRepo extends JpaRepository<InternWorkSchedule, Integer>, JpaSpecificationExecutor<InternWorkSchedule> {
}
