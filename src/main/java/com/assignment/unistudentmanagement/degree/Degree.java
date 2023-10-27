package com.assignment.unistudentmanagement.degree;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "degree")
public class Degree {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer degreeid;

    @Column(nullable = false, length = 255)
    private String degreeName;

    public Integer getDegreeid() {
        return degreeid;
    }

    public void setDegreeid(Integer degreeid) {
        this.degreeid = degreeid;
    }

    public String getDegreeName() {
        return degreeName;
    }

    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
    }

    @Override
    public String toString() {
        return "Degree{" +
                "degreeid=" + degreeid +
                ", degreeName='" + degreeName + '\'' +
                '}';
    }
}
