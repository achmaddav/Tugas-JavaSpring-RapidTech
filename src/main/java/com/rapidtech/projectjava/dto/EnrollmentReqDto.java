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
public class EnrollmentReqDto {

    private Grade grade;
    private Long student_id;
    private Long course_id;
}
