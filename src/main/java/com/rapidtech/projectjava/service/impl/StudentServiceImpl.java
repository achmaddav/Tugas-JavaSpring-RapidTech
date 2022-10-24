package com.rapidtech.projectjava.service.impl;

import com.rapidtech.projectjava.dto.*;
import com.rapidtech.projectjava.model.Course;
import com.rapidtech.projectjava.model.Enrollment;
import com.rapidtech.projectjava.model.Student;
import com.rapidtech.projectjava.repository.CourseRepository;
import com.rapidtech.projectjava.repository.EnrollmentRepository;
import com.rapidtech.projectjava.repository.StudentRepository;
import com.rapidtech.projectjava.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<StudentResDto> getAllStudent(int offset, int pageSize, String field) {
        Page<Student> students = studentRepository.findAll(
                PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
        List<StudentResDto> studentResDtoList = new ArrayList<>();
        for(Student student : students){
            studentResDtoList.add(StudentResDto.builder()
                    .id(student.getId()).lastname(student.getLastname())
                    .firstmidname(student.getFirstmidname())
                    .enrollmentdate(student.getEnrollmentdate())
                    .build());
        }
        return studentResDtoList;
    }

    @Override
    public List<StudentResDto> findAllByName(String firstmidname) {
        Streamable<Student> students = studentRepository.findAllByFirstmidnameContainingIgnoreCase(firstmidname);
        List<StudentResDto> studentResDtoList = new ArrayList<>();
        for(Student student : students){
            studentResDtoList.add(StudentResDto.builder()
                    .id(student.getId()).lastname(student.getLastname())
                    .firstmidname(student.getFirstmidname())
                    .enrollmentdate(student.getEnrollmentdate())
                    .build());
        }
        return studentResDtoList;
    }

    @Override
    public List<StudentWithCourseDto> getAllStudentWithCourse(int offset, int pageSize, String field) {
        Page<Student> students = studentRepository.findAll(
                PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
        List<StudentWithCourseDto> studentWithCourseDtoList = new ArrayList<>();
        for(Student student : students){
            List<EnrollmentWithCourseDto> enrollmentWithCourseDtoList = new ArrayList<>();
            for(Enrollment enrollment : student.getEnrollments()) {
                enrollmentWithCourseDtoList.add(EnrollmentWithCourseDto.builder()
                        .grade(enrollment.getGrade())
                        .courseReqDto(
                                CourseReqDto.builder()
                                        .title(enrollment.getCourse().getTitle())
                                        .credits(enrollment.getCourse().getCredits()).build()).build());
            }
            studentWithCourseDtoList.add(
                    StudentWithCourseDto.builder()
                            .id(student.getId())
                            .lastname(student.getLastname())
                            .firstmidname(student.getFirstmidname())
                            .enrollmentdate(student.getEnrollmentdate())
                            .enrollmentWithCourseDtoList(enrollmentWithCourseDtoList)
                            .build()
            );
        }
        return studentWithCourseDtoList;
    }

    @Override
    public StudentWithCourseDto getStudentWithCourseById(Long id) {
        Student student = studentRepository.findById(id).get();
        List<Enrollment> enrollmentList = student.getEnrollments();
        List<EnrollmentWithCourseDto> enrollmentWithCourseDtoList = new ArrayList<>();
        for (Enrollment enrollment : enrollmentList) {
            enrollmentWithCourseDtoList.add(EnrollmentWithCourseDto.builder()
                    .grade(enrollment.getGrade())
                    .courseReqDto(
                            CourseReqDto.builder()
                                    .title(enrollment.getCourse().getTitle())
                                    .credits(enrollment.getCourse().getCredits()).build())
                    .build());
        }
        return  StudentWithCourseDto.builder()
                .id(student.getId())
                .lastname(student.getLastname())
                .firstmidname(student.getFirstmidname())
                .enrollmentdate(student.getEnrollmentdate())
                .enrollmentWithCourseDtoList(enrollmentWithCourseDtoList)
                .build();
    }

    @Override
    public StudentResDto getStudentById(Long id) {
        Student student = studentRepository.findById(id).get();
        return  StudentResDto.builder()
                .id(student.getId())
                .lastname(student.getLastname())
                .firstmidname(student.getFirstmidname())
                .enrollmentdate(student.getEnrollmentdate())
                .build();
    }

    @Override
    public StudentResDto insertStudent(StudentReqDto studentReqDto) {
        Student newStudent = Student.builder().lastname(studentReqDto.getLastname())
                .firstmidname(studentReqDto.getFirstmidname()).build();
        Student result = studentRepository.save(newStudent);
        return StudentResDto.builder().id(result.getId()).lastname(result.getLastname())
                .firstmidname(result.getFirstmidname()).enrollmentdate(result.getEnrollmentdate())
                .build();
    }

    @Override
    public GetNewStudentWitCourseDto insertNewStudentWithCourse(AddNewStudentToCourseDto addNewStudentToCourseDto) {
        Student newStudent = Student.builder()
                .lastname(addNewStudentToCourseDto.getLastname())
                .firstmidname(addNewStudentToCourseDto.getFirstmidname())
                .build();
        Student student = studentRepository.save(newStudent);
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(
                Student.builder()
                        .id(student.getId())
                        .build()
        );
        enrollment.setCourse(
                Course.builder()
                        .id(addNewStudentToCourseDto.getCourse_id())
                        .build()
        );
        enrollment.setGrade(addNewStudentToCourseDto.getGrade());
        Enrollment result = enrollmentRepository.save(enrollment);
        Course course = courseRepository.findById(result.getCourse().getId()).get();

        return GetNewStudentWitCourseDto.builder()
                .id(student.getId())
                .lastname(student.getLastname())
                .firstmidname(student.getFirstmidname())
                .enrollmentDate(student.getEnrollmentdate())
                .enrollmentWithCourseDto(
                        EnrollmentWithCourseDto.builder()
                            .grade(result.getGrade())
                            .courseReqDto(
                                    CourseReqDto.builder()
                                            .title(course.getTitle())
                                            .credits(course.getCredits())
                                    .build())
                            .build())
                .build();
    }

    @Override
    public StudentResDto updateStudent(Long id, StudentReqDto studentReqDto) {
        Optional<Student> updateStudent = studentRepository.findById(id);
        Student result = new Student();
        if(updateStudent.isPresent()){
            Student student = updateStudent.get();
            student.setLastname(studentReqDto.getLastname());
            student.setFirstmidname(studentReqDto.getFirstmidname());
            result = studentRepository.save(student);
        }
        return StudentResDto.builder().id(result.getId())
                .lastname(result.getLastname())
                .firstmidname(result.getFirstmidname())
                .enrollmentdate(result.getEnrollmentdate())
                .build();
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
