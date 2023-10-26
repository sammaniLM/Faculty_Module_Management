package com.assignment.unistudentmanagement.degree;

import org.springframework.data.repository.CrudRepository;

public interface DegreeRepository extends CrudRepository <Degree, Integer> {
    public Long countByDegreeid(Integer degreeid);
}
