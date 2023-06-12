package com.example.taskAssest.service;

import com.example.taskAssest.Response;
import com.example.taskAssest.entity.Student;

import java.util.List;

public interface IStudentService {


    public Response<Student> addStudent(Student student);
    public Response<Student> deleteStudent(int id);
    public Response<List<Student>> getAllStudent();
    public Response<Student> getStudentById(int id);

    //void registerCourse(int studentId, int courseId);
}
