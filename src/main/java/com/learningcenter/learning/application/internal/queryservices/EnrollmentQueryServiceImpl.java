package com.learningcenter.learning.application.internal.queryservices;

import com.learningcenter.learning.domain.model.aggregates.Enrollment;
import com.learningcenter.learning.domain.model.queries.GetEnrollmentByIdQuery;
import com.learningcenter.learning.domain.model.queries.GetStudentEnrollmentsQuery;
import com.learningcenter.learning.domain.services.EnrollmentQueryService;
import com.learningcenter.learning.infraestructure.persistence.jpa.repositories.EnrollmentRepository;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of EnrollmentQueryService
 *
 */
public class EnrollmentQueryServiceImpl implements EnrollmentQueryService {

    private EnrollmentRepository enrollmentRepository;

    EnrollmentQueryServiceImpl(EnrollmentRepository enrollmentRepository){
        this.enrollmentRepository = enrollmentRepository;
    }

    @Override
    public List<Enrollment> handle(GetStudentEnrollmentsQuery query) {
        return enrollmentRepository.findAllByAcmeStudentRecordId(query.studentRecordId());
    }

    @Override
    public Optional<Enrollment> handle(GetEnrollmentByIdQuery query) {
        return enrollmentRepository.findById(query.enrollmentId());
    }
}
