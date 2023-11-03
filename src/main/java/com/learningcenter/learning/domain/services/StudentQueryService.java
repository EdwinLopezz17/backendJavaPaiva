package com.learningcenter.learning.domain.services;

import com.learningcenter.learning.domain.model.aggregates.Student;
import com.learningcenter.learning.domain.model.queries.GetStudentByProfileIdQuery;

import java.util.Optional;

public interface StudentQueryService {

    Optional<Student> handle(GetStudentByProfileIdQuery query);
}
