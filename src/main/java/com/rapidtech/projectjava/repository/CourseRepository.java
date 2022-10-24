package com.rapidtech.projectjava.repository;

import com.rapidtech.projectjava.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Long> {
}
