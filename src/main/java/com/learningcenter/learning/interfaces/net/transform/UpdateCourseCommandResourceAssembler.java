package com.learningcenter.learning.interfaces.net.transform;

import com.learningcenter.learning.domain.model.commands.UpdateCourseCommand;
import com.learningcenter.learning.interfaces.net.resources.UpdateCourseResource;

public class UpdateCourseCommandResourceAssembler {

    public static UpdateCourseCommand toCommandFromResource(
            Long courseId, UpdateCourseResource resource){
        return  new UpdateCourseCommand(courseId, resource.title(), resource.description());
    }
}
