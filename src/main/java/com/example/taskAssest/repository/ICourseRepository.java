package com.example.taskAssest.repository;

import com.example.taskAssest.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICourseRepository  extends JpaRepository<Course,Integer> {
    Course findByCode(String code);
}
