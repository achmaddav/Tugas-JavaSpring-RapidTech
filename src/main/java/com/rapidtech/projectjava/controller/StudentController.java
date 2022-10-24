package com.rapidtech.projectjava.controller;

import com.rapidtech.projectjava.dto.*;
import com.rapidtech.projectjava.model.Student;
import com.rapidtech.projectjava.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/students")
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/{offset}/{pageSize}/{field}")
    public List<StudentResDto> getAllStudent(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field) {
        return studentService.getAllStudent(offset, pageSize, field);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentResDto insertStudent(@RequestBody StudentReqDto studentReqDto){
        return studentService.insertStudent(studentReqDto);
    }

    @PutMapping("/{id}")
    public StudentResDto put(@PathVariable("id") Long id, @RequestBody StudentReqDto studentReqDto){
        return studentService.updateStudent(id,studentReqDto);
    }

    @GetMapping("/allWithCourse/{offset}/{pageSize}/{field}")
    public List<StudentWithCourseDto> getAllWithCourse(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field){
        return studentService.getAllStudentWithCourse(offset, pageSize, field);
    }

    @GetMapping("/withcourse/{id}")
    public StudentWithCourseDto getStudentWithCourseById(@PathVariable("id") Long id){
        return studentService.getStudentWithCourseById(id);
    }

    @GetMapping("/{id}")
    public StudentResDto getStudentById(@PathVariable("id") Long id){
        return studentService.getStudentById(id);
    }

    @GetMapping("/byname")
    public List<StudentResDto> getStudentByName(@RequestParam String firstmidname){
        return studentService.findAllByName(firstmidname);
    }

    @PostMapping("/withcourse")
    public GetNewStudentWitCourseDto insertStudentWithCourse(@RequestBody AddNewStudentToCourseDto addNewStudentToCourseDto){
        return studentService.insertNewStudentWithCourse(addNewStudentToCourseDto);
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable("id") Long id){
        studentService.deleteStudent(id);
        return "Delete student id: "+id.toString()+" berhasil";
    }
}
