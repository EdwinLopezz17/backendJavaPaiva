package com.learningcenter.learning.domain.services;

import com.learningcenter.learning.domain.model.aggregates.Course;
import com.learningcenter.learning.domain.model.commands.CreateCourseCommand;
import com.learningcenter.learning.domain.model.commands.DeleteCourseCommand;
import com.learningcenter.learning.domain.model.commands.UpdateCourseCommand;

import java.util.Optional;

public interface CourseCommandService {

    Long handle(CreateCourseCommand command);
    Optional<Course> handle(UpdateCourseCommand command);
    void handle(DeleteCourseCommand command);

}
