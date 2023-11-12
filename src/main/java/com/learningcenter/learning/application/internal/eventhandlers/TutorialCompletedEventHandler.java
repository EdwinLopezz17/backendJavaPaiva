package com.learningcenter.learning.application.internal.eventhandlers;

import com.learningcenter.learning.domain.model.commands.UpdateStudentMetricsOnTutorialCompletedCommand;
import com.learningcenter.learning.domain.model.events.TutorialCompletedEvent;
import com.learningcenter.learning.domain.model.queries.GetEnrollmentByIdQuery;
import com.learningcenter.learning.domain.services.EnrollmentQueryService;
import com.learningcenter.learning.domain.services.StudentCommandService;
import org.springframework.context.event.EventListener;

public class TutorialCompletedEventHandler {

    private final StudentCommandService studentCommandService;
    private final EnrollmentQueryService enrollmentQueryService;

    public TutorialCompletedEventHandler(StudentCommandService studentCommandService, EnrollmentQueryService enrollmentQueryService) {
        this.studentCommandService = studentCommandService;
        this.enrollmentQueryService = enrollmentQueryService;
    }

    @EventListener(TutorialCompletedEvent.class)
    public void on(TutorialCompletedEvent event){
        var getEnrollmentByIdQuery = new GetEnrollmentByIdQuery(event.getEnrollmentId());
        var enrollment= enrollmentQueryService.handle(getEnrollmentByIdQuery);
        if(enrollment.isEmpty()){
            var updateStudentMetricsOnTutorialCompletedCommand =
                    new UpdateStudentMetricsOnTutorialCompletedCommand(enrollment.get().getAcmeStudentRecordId());
            studentCommandService.handle(updateStudentMetricsOnTutorialCompletedCommand);
        }
        System.out.println("TutorialCompletedEventHandler executed");

    }

}
