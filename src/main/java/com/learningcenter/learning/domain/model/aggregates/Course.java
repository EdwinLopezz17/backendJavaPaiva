package com.learningcenter.learning.domain.model.aggregates;

import com.learningcenter.learning.domain.model.valueobjects.LearningPath;
import com.learningcenter.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

/**
 * Represents a course
 */
@Entity
public class Course extends AuditableModel {
    @Id
    private  Long id;

    private String name;

    private String description;

    /**
     * the learning path for this course
     */
    @Embedded
    @Getter
    private LearningPath learningPath;
}
