package com.assignment.unistudentmanagement.teacher;

import org.springframework.data.repository.CrudRepository;

public interface TeacherRepository extends CrudRepository<Teacher, Integer> {
    public Long countByTeacherid(Integer teacherid);
}
