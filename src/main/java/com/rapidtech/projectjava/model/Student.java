package com.rapidtech.projectjava.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String lastname;
    private String firstmidname;

    @CreationTimestamp
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(columnDefinition = "timestamp with time zone")
    @JsonFormat(timezone = "GMT+07:00")
    private Date enrollmentdate;

    @OneToMany(mappedBy = "student",fetch = FetchType.LAZY)
    private List<Enrollment> enrollments;
}
