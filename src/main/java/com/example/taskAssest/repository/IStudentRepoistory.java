package com.example.taskAssest.repository;

import com.example.taskAssest.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudentRepoistory extends JpaRepository<Student, Integer> {
    Student findByEmail(String username);
}
