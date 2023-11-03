package com.learningcenter.learning.application.internal.queryservices;

import com.learningcenter.learning.domain.model.aggregates.Student;
import com.learningcenter.learning.domain.model.queries.GetStudentByProfileIdQuery;
import com.learningcenter.learning.domain.services.StudentQueryService;
import com.learningcenter.learning.infraestructure.persistence.jpa.repositories.StudentRepository;

import java.util.Optional;

public class StudentQueryServiceImpl implements StudentQueryService {

    private StudentRepository studentRepository;

    public  StudentQueryServiceImpl(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    /**
     * Query handler to get student by profileId
     * @param query containing profileId
     * @return student
     */
    @Override
    public Optional<Student> handle(GetStudentByProfileIdQuery query) {
        return studentRepository.findByProfileId(query.profileId());
    }



}
