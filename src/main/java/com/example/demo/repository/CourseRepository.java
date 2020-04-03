package com.example.demo.repository;

import com.example.demo.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface CourseRepository extends JpaRepository<Course,Integer> {
    Set<Course> findCoursesBySubjectSubjectId(String subjectId);
    Set<Course> findCoursesByLecturerId(Integer id);

}
