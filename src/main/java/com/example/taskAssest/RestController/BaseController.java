package com.example.taskAssest.RestController;


import com.example.taskAssest.entity.Student;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract  class BaseController {

    public Student getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (Student) authentication.getPrincipal();
    }


}