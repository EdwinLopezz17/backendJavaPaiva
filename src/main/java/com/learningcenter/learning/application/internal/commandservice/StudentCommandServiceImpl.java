package com.learningcenter.learning.application.internal.commandservice;

import com.learningcenter.learning.application.internal.outbundservices.acl.ExternalProfileService;
import com.learningcenter.learning.domain.model.aggregates.Student;
import com.learningcenter.learning.domain.model.commands.CreateStudentCommand;
import com.learningcenter.learning.domain.model.commands.UpdateStudentMetricsOnTutorialCompletedCommand;
import com.learningcenter.learning.domain.model.valueobjects.AcmeStudentRecordId;
import com.learningcenter.learning.domain.services.StudentCommandService;
import com.learningcenter.learning.infraestructure.persistence.jpa.repositories.StudentRepository;
import org.springframework.stereotype.Service;

/**
 * implement of student service
 *
 * <p>
 *     This class i the implementation of the StudentCommandService interface
 *     It is used by the LearningContext to handle command on the student aggregate
 * </p>
 */

@Service
public class StudentCommandServiceImpl implements StudentCommandService {

    private final StudentRepository studentRepository;
    private final ExternalProfileService externalProfileService;

    public StudentCommandServiceImpl(StudentRepository studentRepository, ExternalProfileService externalProfileService) {
        this.studentRepository = studentRepository;
        this.externalProfileService = externalProfileService;
    }

    @Override
    public AcmeStudentRecordId handle(CreateStudentCommand command) {
        var profileId = externalProfileService.fetchProfileIdByEmail(command.email());
        if(profileId.isEmpty()){
            profileId = externalProfileService.createProfileId(command.firstName(), command.LastName(), command.email(), command.streetAddress(),
                    command.number(), command.city(), command.state(), command.zipcode(), command.country() );
        }else{
            studentRepository.findByProfileId(profileId.get()).ifPresent(student ->{
                throw new IllegalArgumentException("The already exists");
            });
        }

        if(profileId.isEmpty()){
            throw  new IllegalArgumentException("Unable to create profile");
        }

        var student = new Student(profileId.get());
        studentRepository.save(student);
        return student.getAcmeStudentRecordId();
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
