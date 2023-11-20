package com.learningcenter.learning.interfaces.net.transform;

import com.learningcenter.learning.domain.model.commands.CreateStudentCommand;
import com.learningcenter.learning.interfaces.net.resources.CreateStudentResource;

public class CreateStudentCommandFromResourceAssembler {

    public static CreateStudentCommand toCommandFromResource(CreateStudentResource resource){
        return  new CreateStudentCommand(resource.name(), resource.lastName(), resource.email(),
                resource.street(), resource.number(), resource.city(), resource.state(),
                resource.zipcode(), resource.country());
    }
}
