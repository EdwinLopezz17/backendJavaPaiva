package com.learningcenter.learning.interfaces.net.transform;

import com.learningcenter.learning.domain.model.aggregates.Enrollment;
import com.learningcenter.learning.interfaces.net.resources.EnrollmentResource;

public class EnrollmentFromEntityAssembler {

    public static EnrollmentResource toResourceFromEntity(Enrollment enrollment){
        return new EnrollmentResource(enrollment.getId(), enrollment.getAcmeStudentRecordId().studentRecordId(),
                enrollment.getCourse().getId(),enrollment.getStatus());
    }
}
