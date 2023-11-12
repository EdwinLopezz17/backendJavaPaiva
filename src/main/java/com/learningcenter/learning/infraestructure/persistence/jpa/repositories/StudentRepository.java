package com.learningcenter.learning.infraestructure.persistence.jpa.repositories;

import com.learningcenter.learning.domain.model.aggregates.Student;
import com.learningcenter.learning.domain.model.valueobjects.AcmeStudentRecordId;
import com.learningcenter.learning.domain.model.valueobjects.ProfileId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByAcmeStudentRecordId(AcmeStudentRecordId acmeStudentRecordId);

    Optional<Student> findByProfileId(ProfileId profileId);



}
