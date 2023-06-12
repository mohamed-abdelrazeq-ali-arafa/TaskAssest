package com.example.taskAssest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "courses")
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COURSE_ID")
    private int courseId;
    @Column(name="NAME")
    private String name;
    @Column(name="CODE")
    private String code;
    @Column(name="DEPARTMENT")
    private String department;
    @Column(name="CREDITHOURS")
    private String creditHours;
    @Column(name="INSTRUCTOR")
    private String instructor;

    @ManyToMany(mappedBy = "courses")
    private List<Student> students;

}
