package com.learningcenter.learning.application.internal.commandservice;

import com.learningcenter.learning.domain.model.aggregates.Course;
import com.learningcenter.learning.domain.model.commands.CreateCourseCommand;
import com.learningcenter.learning.domain.model.commands.DeleteCourseCommand;
import com.learningcenter.learning.domain.model.commands.UpdateCourseCommand;
import com.learningcenter.learning.domain.services.CourseCommandService;
import com.learningcenter.learning.infraestructure.persistence.jpa.repositories.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseCommandServiceImpl implements CourseCommandService {

    private final CourseRepository courseRepository;

    public CourseCommandServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }


    /**
     * @param command
     * @return
     */
    @Override
    public Long handle(CreateCourseCommand command) {
        if(courseRepository.existsByTitle(command.title())){
            throw new IllegalArgumentException("Course with same title already exists");
        }

        var course = new Course(command.title(), command.description());
        courseRepository.save(course);
        return course.getId();
    }

    /**
     * @param command
     * @return
     */
    @Override
    public Optional<Course> handle(UpdateCourseCommand command) {
        return Optional.empty();
    }

    /**
     * @param command
     */
    @Override
    public void handle(DeleteCourseCommand command) {

    }
}
