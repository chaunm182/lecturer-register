package com.example.demo.repository;

import com.example.demo.entity.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CourseRepositoryTest {

    @Autowired
    CourseRepository courseRepository;

    @Test
    void findCoursesBySubjectSubjectId() {
        Set<Course> courses = courseRepository.findCoursesBySubjectSubjectId("INT1450");
        assertEquals(4,courses.size());
    }

    @Test
    void findCoursesByLecturerId() {
        Set<Course> courses = courseRepository.findCoursesByLecturerId(3);
        assertEquals(1,courses.size());
    }
}