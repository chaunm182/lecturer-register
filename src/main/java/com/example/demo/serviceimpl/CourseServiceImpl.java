package com.example.demo.serviceimpl;

import com.example.demo.entity.Course;
import com.example.demo.repository.CourseRepository;
import com.example.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;
import java.util.Set;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Override
    public Set<Course> findCoursesBySubjectId(String subjectId) {
        return courseRepository.findCoursesBySubjectSubjectId(subjectId);
    }

    @Override
    public Set<Course> findCoursesByLecturerId(Integer id) {
        return courseRepository.findCoursesByLecturerId(id);
    }

    @Override
    public Course findById(Integer id) {
        Optional<Course> courseOptional = courseRepository.findById(id);
        if(courseOptional.isPresent()) return courseOptional.get();
        return null;

    }

    @Override
    public void save(Course course) {
        courseRepository.save(course);
    }
}
