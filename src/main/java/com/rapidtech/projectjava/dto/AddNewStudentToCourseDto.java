package com.rapidtech.projectjava.dto;

import com.rapidtech.projectjava.model.Grade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddNewStudentToCourseDto {
    private String lastname;
    private String firstmidname;
    private Long course_id;
    private Grade grade;
}
