package com.rapidtech.projectjava.service;

import com.rapidtech.projectjava.dto.*;


import java.util.List;

public interface StudentService {
    List<StudentResDto> getAllStudent(int offset, int pageSize, String field);
    List<StudentResDto> findAllByName(String firstmidname);
    List<StudentWithCourseDto> getAllStudentWithCourse(int offset, int pageSize, String field);
    StudentWithCourseDto getStudentWithCourseById(Long id);
    StudentResDto getStudentById(Long id);
    StudentResDto insertStudent(StudentReqDto studentReqDto);
    GetNewStudentWitCourseDto insertNewStudentWithCourse(AddNewStudentToCourseDto addNewStudentToCourseDto);
    StudentResDto updateStudent(Long id, StudentReqDto studentReqDto);
    void deleteStudent(Long id);
}
