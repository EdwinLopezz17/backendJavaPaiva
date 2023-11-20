package com.learningcenter.learning.interfaces.net.transform;

import com.learningcenter.learning.domain.model.aggregates.Student;
import com.learningcenter.learning.interfaces.net.resources.StudentResource;

public class StudentResourceFromEntityAssembler {

    public static StudentResource toResourceFromEntity(Student student){
        return new StudentResource(
                student.getStudentRecordId(), student.getProfileId()
        );
    }
}
