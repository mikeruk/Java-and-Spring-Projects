package com.example.spring20232.repository;


import com.example.spring20232.model.entity.Certification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface CertificationRepository extends JpaRepository<Certification, Long> {
    Optional<Certification> findCertificateById(Long id);

}
