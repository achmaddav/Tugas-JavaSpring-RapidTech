package com.rapidtech.projectjava.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseWithStudentDto {
    private Long id;
    private String title;
    private int credits;
    List<EnrollmentWithStudentDto> enrollmentWithStudentDtoList;
}
