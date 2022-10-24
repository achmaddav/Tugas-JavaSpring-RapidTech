package com.rapidtech.projectjava.service;

import com.rapidtech.projectjava.dto.*;

import java.util.List;

public interface CourseService {
    List<CourseResDto> getAllCourse();
    List<CourseWithStudentDto> getAllCourseWithStudent();
    CourseResDto insertCourse(CourseReqDto courseReqDto);
    CourseWithStudentDto getCourseWithStudentById(Long id);
    CourseResDto updateCourse(Long id, CourseReqDto courseReqDto);
    void deleteCourse(Long id);
}
