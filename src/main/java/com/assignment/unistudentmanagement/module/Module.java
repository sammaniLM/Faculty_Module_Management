package com.assignment.unistudentmanagement.module;

import com.assignment.unistudentmanagement.degree.Degree;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name="module")
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer moduleid;

    @Column(nullable = false, length = 255)
    private String moduleName;

    @Column(nullable = false, length = 255)
    private String degreeYear;

    @ManyToMany
    @JoinTable(
            name = "degree_module",
            joinColumns = @JoinColumn(name = "moduleid"),
            inverseJoinColumns = @JoinColumn(name = "degreeid")

    )
    private Set<Degree> degrees;

    public Integer getModuleid() {
        return moduleid;
    }

    public void setModuleid(Integer moduleid) {
        this.moduleid = moduleid;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getDegreeYear() {
        return degreeYear;
    }

    public void setDegreeYear(String degreeYear) {
        this.degreeYear = degreeYear;
    }

    public Set<Degree> getDegrees() {
        return degrees;
    }

    public void setDegrees(Set<Degree> degrees) {
        this.degrees = degrees;
    }

    @Override
    public String toString() {
        return "Module{" +
                "moduleid=" + moduleid +
                ", moduleName='" + moduleName + '\'' +
                ", degreeYear='" + degreeYear + '\'' +
                ", degrees=" + degrees +
                '}';
    }
}
