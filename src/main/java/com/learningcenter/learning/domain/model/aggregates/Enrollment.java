package com.learningcenter.learning.domain.model.aggregates;

import com.learningcenter.learning.domain.model.valueobjects.AcmeStudentRecordId;
import com.learningcenter.learning.domain.model.valueobjects.EnrollmentStatus;
import com.learningcenter.learning.domain.model.valueobjects.ProgressRecord;
import com.learningcenter.learning.domain.model.valueobjects.TutorialId;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

/**
 * Represent an enrollment
 * The enrollment is an aggregate root
 */
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Enrollment extends AbstractAggregateRoot<Enrollment> {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Embedded
    private AcmeStudentRecordId acmeStudentRecordId;

    @Getter
    @ManyToOne
    @JoinColumn(name="course_id")
    private Course course;

    /**
     * The progress record for this enrollment
     */
    private ProgressRecord progressRecord;

    private EnrollmentStatus enrollmentStatus;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    public Enrollment(){

    }

    public Enrollment(AcmeStudentRecordId studentRecordId,Course course){
        this.acmeStudentRecordId = studentRecordId;
        this.course = course;
        this.enrollmentStatus = EnrollmentStatus.REQUESTED;
        this.progressRecord = new ProgressRecord();
    }

    public void confirm(){
        this.enrollmentStatus = EnrollmentStatus.CONFIRMED;
        this.progressRecord.initializeProgressRecord(this, course.getLearningPath());
    }

    public void reject(){
        this.enrollmentStatus = EnrollmentStatus.REJECTED;
    }

    public void cancel(){
        this.enrollmentStatus = EnrollmentStatus.CANCELLED;
    }

    public String getStatus(){
        return this.enrollmentStatus.name().toLowerCase();
    }

    public Long calculateDaysElapsed(){
        return progressRecord.calculateDaysElapsedForEnrollment(this);
    }

    public boolean isConfirm(){
        return this.enrollmentStatus == EnrollmentStatus.CONFIRMED;
    }
    public boolean isReject(){
        return  this.enrollmentStatus == EnrollmentStatus.REJECTED;
    }
    public void completeTutorial(TutorialId tutorialId){
        progressRecord.completeTutorial(tutorialId, course.getLearningPath());
    }
}
