package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CourseController {

    @GetMapping("/course-register")
    public String showCourseRegisterPage(){
        return "course-register";
    }
}
