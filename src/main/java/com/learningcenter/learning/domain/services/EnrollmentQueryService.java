package com.learningcenter.learning.domain.services;

import com.learningcenter.learning.domain.model.aggregates.Enrollment;
import com.learningcenter.learning.domain.model.queries.GetEnrollmentByIdQuery;
import com.learningcenter.learning.domain.model.queries.GetStudentEnrollmentQuery;

import java.util.List;
import java.util.Optional;

public interface EnrollmentQueryService {
    List<Enrollment> handle (GetStudentEnrollmentQuery query);

    Optional<Enrollment> handle(GetEnrollmentByIdQuery query);
}
