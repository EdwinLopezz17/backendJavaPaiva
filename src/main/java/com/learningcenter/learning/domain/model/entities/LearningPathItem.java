package com.learningcenter.learning.domain.model.entities;

import com.learningcenter.learning.domain.model.aggregates.Course;
import com.learningcenter.learning.domain.model.valueobjects.TutorialId;
import com.learningcenter.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class LearningPathItem extends AuditableModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @ManyToOne
    @JoinColumn(name="course_id")
    private Course course;
    @ManyToOne
    @JoinColumn(name="next_item_id")
    private LearningPathItem nextItem;

    @NotNull
    private Long tutorialId;

    public LearningPathItem(Course course, Long tutorialId, LearningPathItem nextItem){
        this.course = course;
        this.tutorialId = tutorialId;
        this.nextItem = nextItem;
    }
    public LearningPathItem(){
        this.tutorialId = 0L;
        this.course = null;
        this.nextItem = null;
    }
    public void updatedNextItem(LearningPathItem nextItem){
        this.nextItem = nextItem;
    }
}
