package com.rapidtech.projectjava.service;

import com.rapidtech.projectjava.dto.EnrollmentDto;
import com.rapidtech.projectjava.dto.EnrollmentReqDto;
import com.rapidtech.projectjava.dto.EnrollmentResDto;
import com.rapidtech.projectjava.dto.StudentCourseReqDto;

import java.util.List;

public interface EnrollmentService {

    List<EnrollmentDto> getAllEnrollment();
    EnrollmentResDto insertEnrollment(EnrollmentReqDto enrollmentReqDto);
    void deleteEnrollment(Long id);
    void deleteByStudent(Long student_id);
    void deleteByStudentAndCourse(StudentCourseReqDto studentCourseReqDto);
}
