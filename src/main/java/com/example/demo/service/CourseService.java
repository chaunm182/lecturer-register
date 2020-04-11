package com.example.demo.service;

import com.example.demo.entity.Course;

import java.util.List;
import java.util.Set;

public interface CourseService {
    Set<Course> findCoursesBySubjectId(String subjectId);
    Set<Course> findCoursesByLecturerId(Integer id);
    Course findById(Integer id);
    void save(Course course);
    List<Course> findAll();
}
