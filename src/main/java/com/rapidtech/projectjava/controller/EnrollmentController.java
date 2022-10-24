package com.rapidtech.projectjava.controller;

import com.rapidtech.projectjava.dto.EnrollmentDto;
import com.rapidtech.projectjava.dto.EnrollmentReqDto;
import com.rapidtech.projectjava.dto.EnrollmentResDto;
import com.rapidtech.projectjava.dto.StudentCourseReqDto;
import com.rapidtech.projectjava.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RequestMapping("/api/enrollments")
@RestController
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @GetMapping("/studentCourse")
    public List<EnrollmentDto> getAll(){
        return enrollmentService.getAllEnrollment();
    }

    @PostMapping
    public EnrollmentResDto post(@RequestBody EnrollmentReqDto enrollmentReqDto){
        return enrollmentService.insertEnrollment(enrollmentReqDto);
    }

    @DeleteMapping("/{id}")
    public String deleteEnrollment(@PathVariable("id") Long id){
        enrollmentService.deleteEnrollment(id);
        return "Delete enrollment id: "+id.toString()+" berhasil";
    }

    @DeleteMapping("/student/{id}")
    public String deleteByStudent(@PathVariable("id")Long id){
        enrollmentService.deleteByStudent(id);
        return "Delete student id: " +id.toString()+" berhasil";
    }

    @DeleteMapping("/studentwithcourse")
    public String deleteStudentAndCourse(@RequestBody StudentCourseReqDto studentCourseReqDto){
        enrollmentService.deleteByStudentAndCourse(studentCourseReqDto);
        return "Delete student id: "+studentCourseReqDto.getStudent_id().toString()
                +" dan course id: "+studentCourseReqDto.getCourse_id().toString()+" berhasil";
    }
}
