package com.assignment.unistudentmanagement.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repository;

    public long getStudentCount() {
        return repository.count();
    }

    public List<Student> getLatestStudents(){
        Pageable pageble = PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "studentid"));
        Page<Student> latestStudents = repository.findAll(pageble);
        return latestStudents.getContent();
    }

    public List<Student> viewAll(){
        return (List<Student>) repository.findAll();
    }

    public void saveStudent(Student student) {
        repository.save(student);
    }
    
    public Student get(Integer studentid) throws StudentNotFoundException{
       Optional<Student> result = repository.findById(studentid);
       if(result.isPresent()){
           return result.get();
       }
       throw new StudentNotFoundException("Student not found under given Student ID" + studentid);
    }

    public void deleteStudent(Integer studentid) throws StudentNotFoundException {
        Long count = repository.countByStudentid(studentid);
        if (count == null || count == 0){
            throw new StudentNotFoundException("Student not found under given Student ID" + studentid);
        }
        repository.deleteById(studentid);
    }
}
