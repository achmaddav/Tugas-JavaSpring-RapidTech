package com.rapidtech.projectjava.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetNewStudentWitCourseDto {
    private Long id;
    private String lastname;
    private String firstmidname;
    @JsonFormat(timezone = "GMT+07:00") // Agar ditampilkan +7, bukan UTC
    private Date enrollmentDate;
    private EnrollmentWithCourseDto enrollmentWithCourseDto;
}
