package com.learningcenter.learning.application.internal.commandservice;

import com.learningcenter.learning.domain.model.aggregates.Student;
import com.learningcenter.learning.domain.model.commands.CreateStudentCommand;
import com.learningcenter.learning.domain.model.commands.UpdateStudentMetricsOnTutorialCompletedCommand;
import com.learningcenter.learning.domain.model.valueobjects.AcmeStudentRecordId;
import com.learningcenter.learning.domain.services.StudentCommandService;
import com.learningcenter.learning.infraestructure.persistence.jpa.repositories.StudentRepository;

public class StudentCommandServiceImpl implements StudentCommandService {

    private final StudentRepository studentRepository;

    public StudentCommandServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public AcmeStudentRecordId handle(CreateStudentCommand command) {
        var student = new Student();
        studentRepository.save(student);
        var Id = new AcmeStudentRecordId();
        return Id;
    }

    @Override
    public AcmeStudentRecordId handle(UpdateStudentMetricsOnTutorialCompletedCommand command) {
        studentRepository.findByAcmeStudentRecordId(command.acmeStudentRecordId()).map(student -> {
            student.updatedMetricsOnTutorialCompleted();
            studentRepository.save(student);
            return  student.getStudentRecordId();
        }).orElseThrow(()->new IllegalArgumentException("Student with given Id nor found"));

        return null;
    }
}
