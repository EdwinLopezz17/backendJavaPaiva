package com.learningcenter.learning.infraestructure.persistence.jpa.repositories;

import com.learningcenter.learning.domain.model.aggregates.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

}
