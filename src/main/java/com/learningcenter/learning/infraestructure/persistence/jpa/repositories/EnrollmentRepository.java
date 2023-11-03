package com.learningcenter.learning.infraestructure.persistence.jpa.repositories;

import com.learningcenter.learning.domain.model.valueobjects.AcmeStudentRecordId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<EnrollmentRepository, Long> {
    List<EnrollmentRepository>findAllByAcmeStudentRecordId(AcmeStudentRecordId acmeStudentRecordId);
}
