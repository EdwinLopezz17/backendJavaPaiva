package com.learningcenter.learning.domain.services;

import com.learningcenter.learning.domain.model.commands.CreateStudentCommand;
import com.learningcenter.learning.domain.model.commands.UpdateStudentMetricsOnTutorialCompletedCommand;
import com.learningcenter.learning.domain.model.valueobjects.AcmeStudentRecordId;

public interface StudentCommandService {
    AcmeStudentRecordId handle (CreateStudentCommand command);

    AcmeStudentRecordId handle(UpdateStudentMetricsOnTutorialCompletedCommand command);
}
