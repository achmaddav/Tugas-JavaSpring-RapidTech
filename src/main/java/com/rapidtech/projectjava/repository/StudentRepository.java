package com.rapidtech.projectjava.repository;

import com.rapidtech.projectjava.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.util.Streamable;

public interface StudentRepository extends JpaRepository<Student,Long> {

    /*@Query("select s from Student s where s.lastname = :lastname or s.firstmidname = :firstmidname")
    List<Student> findByLastnameOrFirstname(String lastname, String firstmidname);*/
    Streamable<Student> findAllByFirstmidnameContainingIgnoreCase(String firstmidname);
}
