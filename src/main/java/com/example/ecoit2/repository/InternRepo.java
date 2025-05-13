package com.example.ecoit2.repository;

import com.example.ecoit2.entity.Intern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface InternRepo extends JpaRepository<Intern, Integer>, JpaSpecificationExecutor<Intern> {

}