package com.rapidtech.projectjava.service.impl;

import com.rapidtech.projectjava.dto.*;
import com.rapidtech.projectjava.model.Student;
import com.rapidtech.projectjava.model.Course;
import com.rapidtech.projectjava.model.Enrollment;
import com.rapidtech.projectjava.repository.StudentRepository;
import com.rapidtech.projectjava.repository.CourseRepository;
import com.rapidtech.projectjava.repository.EnrollmentRepository;
import com.rapidtech.projectjava.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;
/*
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;
*/

    @Override
    public List<EnrollmentDto> getAllEnrollment() {
        List<EnrollmentDto> enrollmentDtoList = new ArrayList<>();
        List<Enrollment> enrollments = enrollmentRepository.findAll();
        for(Enrollment enrollment : enrollments){
            enrollmentDtoList.add(EnrollmentDto.builder()
                    .id(enrollment.getId()).grade(enrollment.getGrade())
                    .studentResDto(
                            StudentResDto.builder()
                                    .id(enrollment.getStudent().getId())
                                    .lastname(enrollment.getStudent().getLastname())
                                    .firstmidname(enrollment.getStudent().getFirstmidname())
                                    .enrollmentdate(enrollment.getStudent().getEnrollmentdate()).build())
                    .courseResDto(
                            CourseResDto.builder()
                                    .id(enrollment.getCourse().getId())
                                    .title(enrollment.getCourse().getTitle())
                                    .credits(enrollment.getCourse().getCredits()).build())
                    .build());
        }
        return enrollmentDtoList;
    }

    @Override
    public EnrollmentResDto insertEnrollment (EnrollmentReqDto enrollmentReqDto) {

        Enrollment newEnrollment = new Enrollment();
        newEnrollment.setGrade(enrollmentReqDto.getGrade());
        newEnrollment.setStudent(
                Student.builder().id(enrollmentReqDto.getStudent_id()).build());
        newEnrollment.setCourse(
                Course.builder().id(enrollmentReqDto.getCourse_id()).build());
        Enrollment result = enrollmentRepository.save(newEnrollment);
        return EnrollmentResDto.builder().id(result.getId())
                .grade(result.getGrade()).build();
    }

    @Override
    public void deleteEnrollment(Long id) {
        enrollmentRepository.deleteById(id);
    }

    @Override
    public void deleteByStudent(Long student_id) {
        enrollmentRepository.deleteByStudent(student_id);
    }

    @Override
    public void deleteByStudentAndCourse(StudentCourseReqDto studentCourseReqDto) {
        enrollmentRepository.deleteByStudentAndCourse(
                studentCourseReqDto.getStudent_id(),
                studentCourseReqDto.getCourse_id());
    }


}
