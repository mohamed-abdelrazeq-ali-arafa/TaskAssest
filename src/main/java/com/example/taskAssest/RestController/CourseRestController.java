package com.example.taskAssest.RestController;

import com.example.taskAssest.Response;
import com.example.taskAssest.entity.Course;
import com.example.taskAssest.implService.CourseService;
import com.example.taskAssest.repository.ICourseRepository;
import com.example.taskAssest.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseRestController {

    @Autowired
    private ICourseService courseService;
    public CourseRestController (ICourseService courseService){
        this.courseService=courseService;
    }
    @RequestMapping(value="/addcourse",method = RequestMethod.POST)
    public Response<Course> addCourse(@RequestBody Course course){
        Response<Course> res =courseService.addCourse(course);
        return res;

    }
    @RequestMapping(value="/getcourse/{theid}",method = RequestMethod.GET)
    public Response<Course> getCourseById(@PathVariable int theid){
        Response<Course> res =courseService.getCourseById(theid);
        return res;
    }

    @RequestMapping(value="/getallcourse",method = RequestMethod.GET)
    public Response<List<Course>> getAllCourse(){
        Response<List<Course>> res =courseService.getAllCourse();
        return res;
    }
    @RequestMapping(value="/deletecourse/{theid}",method = RequestMethod.DELETE)
    public Response<Course> getAllCourse(@PathVariable  int theid){
        Response<Course> res =courseService.deleteCourse(theid);
        return res;
    }



}
