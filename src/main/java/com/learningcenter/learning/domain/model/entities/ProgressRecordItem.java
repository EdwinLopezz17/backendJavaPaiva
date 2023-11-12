package com.learningcenter.learning.domain.model.entities;

import com.learningcenter.learning.domain.model.aggregates.Enrollment;
import com.learningcenter.learning.domain.model.valueobjects.ProgressStatus;
import com.learningcenter.learning.domain.model.valueobjects.TutorialId;
import com.learningcenter.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
public class ProgressRecordItem extends AuditableModel {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name="enrollment_id")
    private Enrollment enrollment;

    @Embedded
    private TutorialId tutorialId;

    private ProgressStatus status;
    private Date startedAt;
    private Date completedAt;

    public ProgressRecordItem(){

    }
    public ProgressRecordItem(Enrollment enrollment, TutorialId tutorialId){
        this.enrollment = enrollment;
        this.tutorialId = tutorialId;
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
