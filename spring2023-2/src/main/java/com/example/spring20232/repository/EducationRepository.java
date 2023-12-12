package com.example.spring20232.repository;


import com.example.spring20232.model.entity.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EducationRepository extends JpaRepository<Education, Long> {


    Optional<Education> findAllById(Long id);

}
