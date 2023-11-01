package com.assignment.unistudentmanagement.student;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository <Student, Integer>{

    public Long countByStudentid(Integer studentid);
    Page<Student> findAll(Pageable pageable);

}
