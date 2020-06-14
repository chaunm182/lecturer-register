package com.example.demo.repository;

import com.example.demo.entity.Course;
import com.example.demo.entity.Subject;
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

    @Test
    void save(){
        Course course = new Course();
        course.setId(1);
        course.setCourseId("D16-099");
        course.setSemester(1);
        Subject subject = new Subject();
        subject.setId(1);
        course.setSubject(subject);


       assertNotNull(courseRepository.save(course));
    }

    @Test
    void delete(){
        courseRepository.deleteById(1);
        assertFalse(courseRepository.findById(1).isPresent());
    }
}