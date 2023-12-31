package com.assignment.unistudentmanagement.module;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface ModuleRepository extends CrudRepository <Module, Integer> {
    public Long countByModuleid(Integer moduleid);
    Page<Module> findAll(Pageable pageable);
}
