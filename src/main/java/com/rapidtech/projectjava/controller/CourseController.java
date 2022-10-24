package com.rapidtech.projectjava.controller;

import com.rapidtech.projectjava.dto.*;
import com.rapidtech.projectjava.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/courses")
@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public List<CourseResDto> getAllCourse() {
        return courseService.getAllCourse();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CourseResDto insertCourse(@RequestBody CourseReqDto courseReqDto){
        return courseService.insertCourse(courseReqDto);
    }

    @GetMapping("/allWithStudent")
    public List<CourseWithStudentDto> getAllWithStudent(){
        return courseService.getAllCourseWithStudent();
    }

    @GetMapping("/withstudent/{id}")
    public CourseWithStudentDto getCourseWithStudentById(@PathVariable("id") Long id){
        return courseService.getCourseWithStudentById(id);
    }

    @PutMapping("/{id}")
    public CourseResDto put(@PathVariable("id") Long id, @RequestBody CourseReqDto courseReqDto){
        return courseService.updateCourse(id,courseReqDto);
    }

    @DeleteMapping("/{id}")
    public String deleteCourse(@PathVariable("id") Long id){
        courseService.deleteCourse(id);
        return "Delete course id: "+id.toString()+" berhasil";
    }
}
