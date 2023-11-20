package com.learningcenter.learning.interfaces.net.resources;

import jakarta.validation.constraints.NotNull;

public record RequestEnrollmentResource(
        @NotNull
        String studentRecordId,
        @NotNull
        Long courseId
) {
}
