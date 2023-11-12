package com.learningcenter.learning.application.internal.commandservice;

import com.learningcenter.learning.domain.exceptions.CourseNotFoundException;
import com.learningcenter.learning.domain.model.aggregates.Course;
import com.learningcenter.learning.domain.model.aggregates.Enrollment;
import com.learningcenter.learning.domain.model.commands.*;
import com.learningcenter.learning.domain.model.valueobjects.TutorialId;
import com.learningcenter.learning.domain.services.EnrollmentCommandService;
import com.learningcenter.learning.infraestructure.persistence.jpa.repositories.CourseRepository;
import com.learningcenter.learning.infraestructure.persistence.jpa.repositories.EnrollmentRepository;
import com.learningcenter.learning.infraestructure.persistence.jpa.repositories.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentCommandServiceImpl implements EnrollmentCommandService {

    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    /**
     *
     * @param enrollmentRepository
     * @param courseRepository
     * @param studentRepository
     */
    public EnrollmentCommandServiceImpl(EnrollmentRepository enrollmentRepository, CourseRepository courseRepository, StudentRepository studentRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public Long handle(RequestEnrollmentCommand command) {
        studentRepository.findByAcmeStudentRecordId(command.acmeStudentRecordId()).map(student -> {
            Course course = courseRepository.findById(command.courseId()).orElseThrow(()->new CourseNotFoundException(command.courseId()));
            Enrollment enrollment = new Enrollment(command.acmeStudentRecordId(), course);
            enrollmentRepository.save(enrollment);
            return enrollment.getId();
        }).orElseThrow(()->new RuntimeException("Student not found"));
        return null;
    }

    @Override
    public Long handle(ConfirmEnrollmentCommand command) {
        enrollmentRepository.findById(command.enrollmentId()).map(enrollment -> {
            enrollment.confirm();
            enrollmentRepository.save(enrollment);
            return enrollment.getId();
        }).orElseThrow(()->new RuntimeException("Enrollment not found"));
        return null;
    }

    @Override
    public Long handle(RejectEnrollmentCommand command) {
        enrollmentRepository.findById(command.enrollmentId()).map(enrollment -> {
            enrollment.reject();
            enrollmentRepository.save(enrollment);
            return enrollment.getId();
        }).orElseThrow(()->new RuntimeException("Enrollment not found"));
        return null;
    }

    @Override
    public Long handle(CancelEnrollmentCommand command) {
        enrollmentRepository.findById(command.enrollmentId()).map(enrollment -> {
            enrollment.cancel();
            enrollmentRepository.save(enrollment);
            return enrollment.getId();
        }).orElseThrow(()->new RuntimeException("Enrollment not found"));
        return null;
    }

    @Override
    public Long handle(CompleteTutorialForEnrollmentCommand command) {
        enrollmentRepository.findById(command.enrollmentId()).map(enrollment -> {
            var tutorialId = new TutorialId(command.tutorialId());
            enrollment.completeTutorial(tutorialId);
            enrollmentRepository.save(enrollment);
            return enrollment.getId();
        }).orElseThrow(()-> new RuntimeException("Enrollment not found"));
        return null;
    }
}
