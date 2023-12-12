package com.example.spring20232.repository;


import com.example.spring20232.model.entity.WorkExp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface WorkExpRepository extends JpaRepository<WorkExp, Long> {


    @Modifying
    @Query("UPDATE WorkExp as w set w.employerName = :employerName,  w.jobTitle = :jobTitle, w.jobDescription = :jobDescription, w.responsibilities = :responsibilities, w.startDate = :startDate, w.endDate = :endDate, w.stillPresent = :stillPresent where w.id = :id ")
    void updateEntity(@Param("id") Long id,
                      @Param("employerName") String employerName,
                      @Param("jobTitle") String jobTitle,
                      @Param("jobDescription") String jobDescription,
                      @Param("responsibilities") String responsibilities,
                      @Param("startDate") LocalDate startDate,
                      @Param("endDate") LocalDate endDate,
                      @Param("stillPresent") boolean stillPresent);





    Optional<WorkExp> findAllById(Long id);




}
