package com.learningcenter.learning.infraestructure.persistence.jpa.repositories;

import com.learningcenter.learning.domain.model.aggregates.Enrollment;
import com.learningcenter.learning.domain.model.valueobjects.AcmeStudentRecordId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment>findAllByAcmeStudentRecordId(AcmeStudentRecordId acmeStudentRecordId);
}
