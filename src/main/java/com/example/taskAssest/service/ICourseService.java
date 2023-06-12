package com.example.taskAssest.service;

import com.example.taskAssest.Response;
import com.example.taskAssest.entity.Course;

import java.util.List;

public interface ICourseService {

    public Response<Course> addCourse(Course course);
    public Response<Course> deleteCourse(int id);
    public Response<List<Course>> getAllCourse();
    public Response<Course> getCourseById(int id);
}
