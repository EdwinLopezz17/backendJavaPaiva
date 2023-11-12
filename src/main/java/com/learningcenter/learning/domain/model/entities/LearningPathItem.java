package com.learningcenter.learning.domain.model.entities;

import com.learningcenter.learning.domain.model.aggregates.Course;
import com.learningcenter.learning.domain.model.valueobjects.TutorialId;
import com.learningcenter.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
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

    @Embedded
    private TutorialId tutorialId;

    public LearningPathItem(Course course, TutorialId tutorialId, LearningPathItem nextItem){
        this.course = course;
        this.tutorialId = tutorialId;
        this.nextItem = nextItem;
    }
    public LearningPathItem(){
        this.tutorialId = new TutorialId();
        this.course = null;
        this.nextItem = null;
    }
    public void updatedNextItem(LearningPathItem nextItem){
        this.nextItem = nextItem;
    }
}
