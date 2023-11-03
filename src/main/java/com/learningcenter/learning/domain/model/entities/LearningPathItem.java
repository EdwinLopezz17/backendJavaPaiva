package com.learningcenter.learning.domain.model.entities;

import com.learningcenter.learning.domain.model.aggregates.Course;
import com.learningcenter.shared.domain.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
public class LearningPathItem extends AuditableModel {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Getter
    @ManyToOne
    @JoinColumn(name="course_id")
    private Course course;
    @ManyToOne
    @Getter
    @JoinColumn(name="next_item_id")
    private LearningPathItem nextItem;

    @Getter
    @ManyToOne(optional = false)
    private Tutorial tutorial;

    public LearningPathItem(Course course, Tutorial tutorial, LearningPathItem nextItem){
        this.course = course;
        this.tutorial = tutorial;
        this.nextItem = nextItem;
    }
    public LearningPathItem(){
        this.course = null;
        this.nextItem = null;
    }
    public void updatedNextItem(LearningPathItem nextItem){
        this.nextItem = nextItem;
    }
}
