package com.example.taskAssest.implService;

import com.example.taskAssest.Response;
import com.example.taskAssest.RestController.BaseController;
import com.example.taskAssest.entity.Course;
import com.example.taskAssest.repository.ICourseRepository;
import com.example.taskAssest.service.ICourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "course")
public class CourseService  extends BaseController implements ICourseService {

    @Autowired
    private ICourseRepository courseRepository;
    public CourseService(ICourseRepository courseRepository){this.courseRepository=courseRepository;}



    private static final Logger LOGGER = LoggerFactory.getLogger(CourseService.class);


    @Override
    public Response<Course> addCourse(Course course) {
        LOGGER.info("start of CourseService.addCourse with course id "+course.getCourseId());
        var res=new Response<Course>();
        Course temp=courseRepository.findByCode(course.getCode());
//        int newid=getCurrentUser().getId();
//        LOGGER.info("the newset id og the user is"+newid);

        if(temp!=null)
            res.make("There is Course with this Code",400,temp);
        else {
            courseRepository.save(course);
            res.make("Success Insertion of course", 201, course);
        }
        LOGGER.info("end of UserService.addCourse with course id "+course.getCourseId());
        return  res;
    }

    @Override
    public Response<Course> deleteCourse(int id) {
        int tempid=id;
        LOGGER.info("start of CourseService.deleteCourse with course id "+id);
        var res=new Response<Course>();
        Course temp= courseRepository.findById(id).orElse(null);

        if(temp!=null) {
            courseRepository.deleteById(id);
            res.make("Success Deletion of Course", 201, temp);
        }
        else {
            res.make("There is No id found ", 201, temp);
        }
        LOGGER.info("end of CourseService.delete with course id "+tempid);
        return  res;
    }


    @Override
    public Response<List<Course>> getAllCourse() {
        LOGGER.info("start of CourseService.getAllCourse");
        var res=new Response<List<Course>>();
        List<Course>courses=courseRepository.findAll();
        res.make("Success Retrive", 201, courses);
        LOGGER.info("end of CourseService.getAllCourse");
        return res;
    }

    @Override
    @Cacheable(value = "coursecash", key = "#id")
    public Response<Course> getCourseById(int id) {
        LOGGER.info("start of CourseService.getCourseById with user id "+id);
        var res=new Response<Course>();
        Course result = courseRepository.findById(id).orElse(null);
        if(result==null)
        {
            res.make("Failed to retrive", 400, result);
        }
        else {
            res.make("Success Retrive", 201, result);
        }
        LOGGER.info("end of CourseService.getCourseById with user id "+id);
        return res;
    }
}
