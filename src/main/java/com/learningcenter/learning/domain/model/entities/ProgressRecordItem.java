package com.learningcenter.learning.domain.model.entities;

import com.learningcenter.learning.domain.model.aggregates.Enrollment;
import com.learningcenter.learning.domain.model.valueobjects.ProgressStatus;
import com.learningcenter.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Date;

@Entity
public class ProgressRecordItem extends AuditableModel {
    @Id
    private Long id;

    @Getter
    @ManyToOne
    @JoinColumn(name="enrollment_id")
    private Enrollment enrollment;

    @Getter
    @ManyToOne
    @JoinColumn(name="tutorial_id")
    private Tutorial tutorial;

    private ProgressStatus status;
    private Date startedAt;
    private Date completedAt;

    public ProgressRecordItem(){

    }
    public ProgressRecordItem(Enrollment enrollment, Tutorial tutorial){
        this.enrollment = enrollment;
        this.tutorial = tutorial;
        this.status = ProgressStatus.NOT_STARTED;
    }

    public  void start(){
        this.status = ProgressStatus.STARTED;
        this.startedAt = new Date();
    }
    public  void completed(){
        this.status = ProgressStatus.COMPLETED;
        this.completedAt = new Date();
    }

    public boolean isCompleted(){
        return this.status ==ProgressStatus.COMPLETED;
    }
    public boolean isInProgress(){
        return this.status==ProgressStatus.STARTED;
    }
    public boolean isNotStarted(){
        return this.status == ProgressStatus.NOT_STARTED;
    }

    public Long calculateDaysElapsed(){
        if (this.status == ProgressStatus.NOT_STARTED) return 0L;
        if(this.status == ProgressStatus.COMPLETED)
            return java.time.Duration.between(this.startedAt.toInstant(), this.completedAt.toInstant()).toDays();

        var defaultTimeZone = java.time.ZoneId.systemDefault();
        var toDate = LocalDate.now().atStartOfDay(defaultTimeZone).toInstant();

        return java.time.Duration.between(this.startedAt.toInstant(), toDate).toDays();
    }
}
