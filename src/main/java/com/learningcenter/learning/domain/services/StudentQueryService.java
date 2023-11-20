package com.learningcenter.learning.domain.services;

import com.learningcenter.learning.domain.model.aggregates.Student;
import com.learningcenter.learning.domain.model.queries.GetStudentByAcmeStudentRecordIQuery;
import com.learningcenter.learning.domain.model.queries.GetStudentByProfileIdQuery;

import java.util.Optional;

public interface StudentQueryService {

    Optional<Student> handle(GetStudentByProfileIdQuery query);
    Optional<Student> handle(GetStudentByAcmeStudentRecordIQuery query);



}
