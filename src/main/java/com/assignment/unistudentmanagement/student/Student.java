package com.assignment.unistudentmanagement.student;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "studentid")
    private Integer studentid;

    @Column(nullable = false, length = 255, name = "full_name")
    private String full_name;

    @Column(nullable = false)
    private Date dob;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date enrollment_date;

    public boolean isEnable_student() {
        return enable_student;
    }

    public void setEnable_student(boolean enable_student) {
        this.enable_student = enable_student;
    }

    private boolean enable_student;

    public Integer getStudentid() {
        return studentid;
    }

    public void setStudentid(Integer studentid) {
        this.studentid = studentid;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getEnrollment_date() {
        return enrollment_date;
    }

    public void setEnrollment_date(Date enrollment_date) {
        this.enrollment_date = enrollment_date;
    }

    @Override
    public String toString() {
        return "Student{" +
                "student_id=" + studentid +
                ", full_name='" + full_name + '\'' +
                ", dob=" + dob +
                ", email='" + email + '\'' +
                ", enrollment_date=" + enrollment_date +
                '}';
    }
}
