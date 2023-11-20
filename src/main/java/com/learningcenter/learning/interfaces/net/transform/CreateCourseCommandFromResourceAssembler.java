package com.learningcenter.learning.interfaces.net.transform;

import com.learningcenter.learning.interfaces.net.resources.CreateCourseResource;

public class CreateCourseCommandFromResourceAssembler {
    public static CreateCourseResource toCommandFromResource(CreateCourseResource resource){
        return new CreateCourseResource(resource.title(), resource.description());
    }
}
