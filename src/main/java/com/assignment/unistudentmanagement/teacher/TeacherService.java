package com.assignment.unistudentmanagement.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository repository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public List<Teacher> viewAll(){
        return (List<Teacher>) repository.findAll();
    }

    public void saveTeacherRecord(Teacher teacher){
        String encodedPassword = passwordEncoder.encode(teacher.getPassword());
        teacher.setPassword(encodedPassword);
        repository.save(teacher);
    }

    public Teacher get(Integer teacherid) throws TeacherNotFoundException{
        Optional<Teacher> result = repository.findById(teacherid);
        if(result.isPresent()){
            return result.get();
        }
        throw new TeacherNotFoundException("Teacher not found under given Teacher ID" + teacherid);
    }

    public void deleteTeacherRecord(Integer teacherid) throws TeacherNotFoundException{
        Long count = repository.countByTeacherid(teacherid);
        if(count == null || count == 0){
            throw new TeacherNotFoundException("Teacher not found under given Teacher ID" + teacherid);
        }
        repository.deleteById(teacherid);
    }
}
