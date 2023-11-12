package com.learningcenter.learning.domain.model.commands;

public record UpdateCourseCommand(Long courseId, String title, String description) {
}
