package com.learningcenter.learning.domain.model.commands;

import com.learningcenter.learning.domain.model.valueobjects.AcmeStudentRecordId;

public record UpdateStudentMetricsOnTutorialCompletedCommand(AcmeStudentRecordId acmeStudentRecordId) {

}
