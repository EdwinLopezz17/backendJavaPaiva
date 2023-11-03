package com.learningcenter.learning.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record StudentPerformanceMetrics(Integer totalCompleteCourses, Integer totalTutorials) {
    public  StudentPerformanceMetrics(){
        this(0,0);
    }
    public  StudentPerformanceMetrics{
        if(totalCompleteCourses <0){
            throw new IllegalArgumentException("Total completed courses cannot be negative");
        }
        if(totalTutorials <0){
            throw new IllegalArgumentException("Total tutorials cannot be negative");
        }
    }

    public StudentPerformanceMetrics incrementTotalCompletedCourses(){
        return new StudentPerformanceMetrics(totalCompleteCourses+1, totalTutorials);
    }
    public StudentPerformanceMetrics incrementTotalTutorials(){
        return new StudentPerformanceMetrics(totalCompleteCourses, totalTutorials+1);
    }

}
