package com.learningcenter.learning.domain.model.aggregates;

import com.learningcenter.learning.domain.model.valueobjects.AcmeStudentRecordId;
import com.learningcenter.learning.domain.model.valueobjects.ProfileId;
import com.learningcenter.learning.domain.model.valueobjects.StudentPerformanceMetrics;
import com.learningcenter.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Student extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @Column(name="acme_student_id")
    private AcmeStudentRecordId acmeStudentRecordId;


    @Embedded
    private ProfileId profileId;

    @Embedded
    private StudentPerformanceMetrics performanceMetrics;

    public Student(){
        this.acmeStudentRecordId = new AcmeStudentRecordId();
        this.performanceMetrics = new StudentPerformanceMetrics();
    }

    public Student(ProfileId profileId){
        this();
        this.profileId = new ProfileId;
    }
    public void updateMetricsOnCoursesCompleted(){
        this.performanceMetrics = this.performanceMetrics.incrementTotalCompletedCourses();
    }
    public void updatedMetricsOnTutorialCompleted(){
        this.performanceMetrics = this.performanceMetrics.incrementTotalTutorials();
    }
    public String getStudentRecordId(){
        return this.acmeStudentRecordId.studentRecordId();
    }
    public Long getProfileId(){
        return this.profileId.profileId();
    }
}
