package com.assignment.unistudentmanagement.teacher;

import com.assignment.unistudentmanagement.module.Module;
import jakarta.persistence.*;

import java.util.Set;


@Entity
@Table(name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer teacherid;

    @Column(nullable = false, length = 255)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "teacher_modules",
            joinColumns = @JoinColumn(name = "teacherid"),
            inverseJoinColumns = @JoinColumn(name = "moduleid")
    )
    private Set<Module> modules;

    @Column(nullable = false, length = 100)
    private String username;

    @Column(nullable = false, length = 255)
    private String password;

    public Integer getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(Integer teacherid) {
        this.teacherid = teacherid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Module> getModules() {
        return modules;
    }

    public void setModules(Set<Module> modules) {
        this.modules = modules;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherid=" + teacherid +
                ", name='" + name + '\'' +
                ", modules=" + modules +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
