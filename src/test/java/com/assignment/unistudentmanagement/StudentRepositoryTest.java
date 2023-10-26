package com.assignment.unistudentmanagement;

import com.assignment.unistudentmanagement.student.Student;
import com.assignment.unistudentmanagement.student.StudentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class StudentRepositoryTest {
    @Autowired private StudentRepository repo;

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    public void testAddNew(){
        Date dateOfBirth = null;
        Date enrollDate = null;

        try{
            dateOfBirth = dateFormat.parse("1985-03-31");
            enrollDate = dateFormat.parse("2023-01-01");
        }catch (Exception e){
            System.out.println("Wrong Format");
        }

        Student student = new Student();
        student.setDob(dateOfBirth);
        student.setEmail("amithlabugama@gmail.com");
        student.setEnrollment_date(enrollDate);
        student.setFull_name("Amith Ariyawansa");

        Student saveData = repo.save(student);

        Assertions.assertThat(saveData).isNotNull();
        Assertions.assertThat(saveData.getStudentid()).isGreaterThan(0);
    }

    @Test
    public void testViewAll(){
        Iterable<Student> students = repo.findAll();
        Assertions.assertThat(students).hasSizeGreaterThan(0);

        for (Student student : students){
            System.out.println(student);
        }
    }

    @Test
    public void updateStudent(){
        Integer studentid = 9;
        Optional<Student> optionalStudent = repo.findById(studentid);
        Student student = optionalStudent.get();
        student.setEmail("qqqasaniqqsammani@gmail.com");
        repo.save(student);

        Student updatedStudent = repo.findById(studentid).get();
        Assertions.assertThat(updatedStudent.getEmail()).isEqualTo("qqqasaniqqsammani@gmail.com");
    }

    @Test
    public void viewStudent(){
        Integer studentid = 9;
        Optional<Student> optionalStudent = repo.findById(studentid);
        Assertions.assertThat(optionalStudent).isPresent();
        System.out.println(optionalStudent.get());
    }

    @Test
    public void testStudentDelete(){
        Integer studentid = 9;
        repo.deleteById(studentid);

        Optional<Student> optionalStudent = repo.findById(studentid);
        Assertions.assertThat(optionalStudent).isNotPresent();
    }
}
