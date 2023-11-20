package com.learningcenter.learning.interfaces.net.transform;

import com.learningcenter.learning.domain.model.commands.RequestEnrollmentCommand;
import com.learningcenter.learning.domain.model.valueobjects.AcmeStudentRecordId;
import com.learningcenter.learning.interfaces.net.resources.RequestEnrollmentResource;

public class RequestEnrollmentCommandFromResourceAssembler {


    public static RequestEnrollmentCommand toCommandFromResource(RequestEnrollmentResource resource){
        return new RequestEnrollmentCommand(new AcmeStudentRecordId(resource.studentRecordId()), resource.courseId());
    }
}
