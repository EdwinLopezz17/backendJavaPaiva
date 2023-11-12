package com.learningcenter.learning.domain.services;

import com.learningcenter.learning.domain.model.aggregates.Course;
import com.learningcenter.learning.domain.model.queries.GetAllCoursesQuery;
import com.learningcenter.learning.domain.model.queries.GetCourseByIdQuery;

import java.util.List;
import java.util.Optional;

public interface CourseQueryService {

    Optional<Course> handle(GetCourseByIdQuery query);
    List<Course> handle(GetAllCoursesQuery query);



}
