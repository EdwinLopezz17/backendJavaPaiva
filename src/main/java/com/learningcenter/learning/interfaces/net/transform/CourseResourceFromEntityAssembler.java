package com.learningcenter.learning.interfaces.net.transform;

import com.learningcenter.learning.domain.model.aggregates.Course;
import com.learningcenter.learning.interfaces.net.resources.CourseResource;

public class CourseResourceFromEntityAssembler {

    public static CourseResource toResourceFromEntity(Course entity){
        return  new CourseResource(entity.getId(), entity.getTitle(), entity.getDescription());
    }
}
