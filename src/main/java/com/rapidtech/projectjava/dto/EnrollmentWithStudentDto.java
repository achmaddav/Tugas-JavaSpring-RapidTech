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
public class EnrollmentWithStudentDto {

    private Grade grade;
    private StudentReqDto studentReqDto;
}
