package com.rapidtech.projectjava.service.impl;

import com.rapidtech.projectjava.dto.*;
import com.rapidtech.projectjava.model.Course;
import com.rapidtech.projectjava.model.Enrollment;
import com.rapidtech.projectjava.model.Student;
import com.rapidtech.projectjava.repository.CourseRepository;
import com.rapidtech.projectjava.repository.StudentRepository;
import com.rapidtech.projectjava.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<CourseResDto> getAllCourse() {
        List<Course> courses = courseRepository.findAll();
        List<CourseResDto> courseResDtoList = new ArrayList<>();
        for(Course course : courses){
            courseResDtoList.add(CourseResDto.builder()
                    .id(course.getId())
                    .title(course.getTitle())
                    .credits(course.getCredits())
                    .build());
        }
        return courseResDtoList;
    }

    @Override
    public List<CourseWithStudentDto> getAllCourseWithStudent() {
        List<Course> courses = courseRepository.findAll();
        List<CourseWithStudentDto> courseWithStudentDtoList = new ArrayList<>();
        for(Course course : courses){
            List<EnrollmentWithStudentDto> enrollmentWithStudentDtoList = new ArrayList<>();
            for(Enrollment enrollment : course.getEnrollments()) {
                enrollmentWithStudentDtoList.add(EnrollmentWithStudentDto.builder()
                        .grade(enrollment.getGrade())
                        .studentReqDto(
                                StudentReqDto.builder()
                                        .lastname(enrollment.getStudent().getLastname())
                                        .firstmidname(enrollment.getStudent().getFirstmidname())
                                        .build()).build());
            }
            courseWithStudentDtoList.add(
                    CourseWithStudentDto.builder()
                            .id(course.getId())
                            .title(course.getTitle())
                            .credits(course.getCredits())
                            .enrollmentWithStudentDtoList(enrollmentWithStudentDtoList)
                            .build()
            );
        }
        return courseWithStudentDtoList;
    }

    @Override
    public CourseResDto insertCourse(CourseReqDto courseReqDto) {
        Course newCourse = Course.builder().title(courseReqDto.getTitle())
                .credits(courseReqDto.getCredits())
                .build();
        Course result = courseRepository.save(newCourse);
        return CourseResDto.builder().id(result.getId()).title(result.getTitle())
                .credits(result.getCredits())
                .build();
    }

    @Override
    public CourseWithStudentDto getCourseWithStudentById(Long id) {
        Course course = courseRepository.findById(id).get();
        List<Enrollment> enrollmentList = course.getEnrollments();
        List<EnrollmentWithStudentDto> enrollmentWithStudentDtoList = new ArrayList<>();
        for (Enrollment enrollment : enrollmentList) {
            enrollmentWithStudentDtoList.add(EnrollmentWithStudentDto.builder()
                    .grade(enrollment.getGrade())
                    .studentReqDto(
                            StudentReqDto.builder()
                                    .lastname(enrollment.getStudent().getLastname())
                                    .firstmidname(enrollment.getStudent().getFirstmidname()).build())
                    .build());
        }
        return  CourseWithStudentDto.builder()
                .id(course.getId())
                .title(course.getTitle())
                .credits(course.getCredits())
                .enrollmentWithStudentDtoList(enrollmentWithStudentDtoList)
                .build();
    }

    @Override
    public CourseResDto updateCourse(Long id, CourseReqDto courseReqDto) {
        Optional<Course> updateCourse = courseRepository.findById(id);
        Course result = new Course();
        if(updateCourse.isPresent()){
            Course course = updateCourse.get();
            course.setTitle(courseReqDto.getTitle());
            course.setCredits(courseReqDto.getCredits());
            result = courseRepository.save(course);
        }
        return CourseResDto.builder().id(result.getId())
                .title(result.getTitle())
                .credits(result.getCredits())
                .build();
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}
