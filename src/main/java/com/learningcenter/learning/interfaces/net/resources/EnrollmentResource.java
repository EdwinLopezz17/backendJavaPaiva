package com.learningcenter.learning.interfaces.net.resources;

public record EnrollmentResource(
        Long enrollmentId, String studentRecordId, Long courseId, String status) {
}
