package com.example.taskAssest.RestController;

import com.example.taskAssest.Response;
import com.example.taskAssest.entity.Student;
import com.example.taskAssest.implService.StudentService;
import com.example.taskAssest.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/student")
public class StudentRestController  extends  BaseController{

     @Autowired
     private IStudentService studentService;
     public StudentRestController(StudentService studentService){this.studentService=studentService;}

    @RequestMapping(value="/addstudent",method = RequestMethod.POST)
    public Response<Student> addStudent(@RequestBody Student student){
        Response<Student> res =studentService.addStudent(student);
        return res;

    }
    @RequestMapping(value="/getstudent/{theid}",method = RequestMethod.GET)
    public Response<Student> getStudentById(@PathVariable int theid){
        Response<Student> res =studentService.getStudentById(theid);
        return res;
    }

    @RequestMapping(value="/getallstudent",method = RequestMethod.GET)
    public Response<List<Student>> getAllStudent(){
        Response<List<Student>> res =studentService.getAllStudent();
        return res;
    }
    @RequestMapping(value="/deletestudent/{theid}",method = RequestMethod.DELETE)
    public Response<Student> getAllStudent(@PathVariable int theid){
        Response<Student> res =studentService.deleteStudent(theid);
        return res;
    }


//    @PostMapping("/{studentId}/courses/{courseId}/register")
//    public ResponseEntity<String> registerCourse(@PathVariable int studentId, @PathVariable int courseId) {
//        studentService.registerCourse(studentId, courseId);
//        return ResponseEntity.ok("Course registered successfully");
//    }











}
