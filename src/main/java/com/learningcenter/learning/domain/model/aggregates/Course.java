package com.learningcenter.learning.domain.model.aggregates;

import com.learningcenter.learning.domain.model.valueobjects.LearningPath;
import com.learningcenter.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;

/**
 * Represents a course
 */
@Entity
@Getter
@Setter
public class Course extends AuditableModel {
    @Id
    private  Long id;

    private String title;

    private String description;

    /**
     * the learning path for this course
     */
    @Embedded
    @Getter
    private LearningPath learningPath;

    public Course(String title, String description) {
        this();
        this.title = title;
        this.description = description;
    }

    public Course() {
        this.title = Strings.EMPTY;
        this.description = Strings.EMPTY;
        this.learningPath = new LearningPath();
    }
}
