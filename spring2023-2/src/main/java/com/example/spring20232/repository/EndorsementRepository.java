package com.example.spring20232.repository;

import com.example.spring20232.model.entity.Endorsement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EndorsementRepository extends JpaRepository<Endorsement, Long> {
}
