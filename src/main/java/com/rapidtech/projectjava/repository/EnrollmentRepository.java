package com.rapidtech.projectjava.repository;

import com.rapidtech.projectjava.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface EnrollmentRepository extends JpaRepository<Enrollment,Long> {

    @Modifying
    @Query(nativeQuery = true, value =
            "DELETE FROM enrollment WHERE student_id = ?1")
    void deleteByStudent(Long student_id);

    @Modifying
    @Query(nativeQuery = true, value =
            "DELETE FROM enrollment WHERE student_id = ?1 and course_id = ?2")
    void deleteByStudentAndCourse(Long student_id, Long course_id);
}
