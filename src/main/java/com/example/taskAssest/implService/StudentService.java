package com.example.taskAssest.implService;

import com.example.taskAssest.Response;
import com.example.taskAssest.entity.Course;
import com.example.taskAssest.entity.Student;
import com.example.taskAssest.repository.ICourseRepository;
import com.example.taskAssest.repository.IStudentRepoistory;
import com.example.taskAssest.service.IStudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = "student")
public class StudentService implements IStudentService,UserDetailsService {
    @Autowired
    private IStudentRepoistory studentRepoistory;

    @Autowired
    private ICourseRepository courseRepository;
    public StudentService(IStudentRepoistory studentRepoistory){
        this.studentRepoistory=studentRepoistory;
    }
    @Bean
    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student user = studentRepoistory.findByEmail(username);
        if (user == null) {
            System.out.println("failed to find this email");
        }
        return user;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentService.class);
    @Override
    public Response<Student> addStudent(Student student) {
        LOGGER.info("start of UserService.addUser with user id "+student.getId());
        student.setPassword(passwordEncoder().encode(student.getPassword()));
        var res=new Response<Student>();
        Student temp=studentRepoistory.findByEmail(student.getEmail());

        if(temp!=null)
            res.make("There is Email with this Name",400,temp);
        else {
            student.setId(150);
            studentRepoistory.save(student);
            System.out.println(student.getId());
            res.make("Success Insertion of Student", 201, student);
        }
        LOGGER.info("end of StudentService.addStudent with user id "+student.getId());
        return  res;
    }

    @Override
    public Response<Student> deleteStudent(int id) {
        int tempid=id;
        LOGGER.info("start of UserService.deleteUser with user id "+id);
        var res=new Response<Student>();
       Student temp= studentRepoistory.findById(id).orElse(null);

        if(temp!=null) {
            studentRepoistory.deleteById(id);
            res.make("Success Deletion of Student", 201, temp);
        }
        else {
            res.make("There is No id found ", 201, temp);
        }
        LOGGER.info("end of UserService.delete with user id "+tempid);
        return  res;
    }

    @Override
    public Response<List<Student>> getAllStudent() {
        LOGGER.info("start of StudentService.getAllStudent");
        var res=new Response<List<Student>>();
        List<Student>students=studentRepoistory.findAll();
        res.make("Success Retrive", 201, students);
        LOGGER.info("end of StudentService.getAllStudent");
        return res;
    }

    @Override
    @Cacheable(value = "coursecash", key = "#id")
    public Response<Student> getStudentById(int id) {
        LOGGER.info("start of UserService.getStudentById with user id "+id);
        var res=new Response<Student>();
        Student result = studentRepoistory.findById(id).orElse(null);
        if(result==null)
        {
            res.make("Failed to retrive", 400, result);
        }
        else {
            res.make("Success Retrive", 201, result);
        }
        LOGGER.info("end of UserService.getStudentById with user id "+id);
        return res;
    }



//    public void registerCourse(int studentId, int courseId) {
//        Student student = studentRepoistory.findById(studentId).orElseThrow(() ->
//                new IllegalArgumentException("Invalid student ID"));
//
//        Course course = courseRepository.findById(courseId).orElseThrow(() ->
//                new IllegalArgumentException("Invalid course ID"));
//
//        student.registerCourse(course);
//        studentRepoistory.save(student);
//    }



}
