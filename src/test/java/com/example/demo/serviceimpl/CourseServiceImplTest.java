package com.example.demo.serviceimpl;

import com.example.demo.entity.Course;
import com.example.demo.repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class CourseServiceImplTest {

    @InjectMocks
    CourseServiceImpl courseService;

    @Mock
    CourseRepository courseRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findByIdWhenCourseOptionalIsPresent() {
        Course course = new Course();
        course.setId(1);
        Optional<Course> courseOptional = Optional.of(course);
        when(courseRepository.findById(anyInt())).thenReturn(courseOptional);

        Course course1 = courseService.findById(anyInt());

        assertEquals(courseOptional.get(),course1);

    }

    @Test
    void findByIdWhenCourseOptionalIsNotPresent() {
        Optional<Course> courseOptional = Optional.empty();
        when(courseRepository.findById(anyInt())).thenReturn(courseOptional);

        Course course1 = courseService.findById(anyInt());

        assertNull(course1);

    }

    @Test
    void findCoursesBySubjectId(){
        Set<Course> courses = new HashSet<>();
        Course course = new Course();
        course.setId(1);
        Course course1 = new Course();
        course1.setId(2);
        courses.add(course);
        courses.add(course1);

        when(courseRepository.findCoursesBySubjectSubjectId(anyString())).thenReturn(courses);

        assertEquals(courseService.findCoursesBySubjectId(anyString()),courses);
    }

    @Test
    void findCoursesByLecturerId(){
        Set<Course> courses = new HashSet<>();
        Course course = new Course();
        course.setId(1);
        Course course1 = new Course();
        course1.setId(2);
        courses.add(course);
        courses.add(course1);

        when(courseRepository.findCoursesByLecturerId(anyInt())).thenReturn(courses);

        assertEquals(courseService.findCoursesByLecturerId(anyInt()),courses);
    }

    @Test
    void findAll(){
        List<Course> courses = new ArrayList<>();
        Course course = new Course();
        course.setId(1);
        Course course1 = new Course();
        course1.setId(2);
        courses.add(course);
        courses.add(course1);

        when(courseRepository.findAll(Sort.by("courseId"))).thenReturn(courses);

        assertEquals(courses,courseService.findAll());

    }

    @Test
    void save(){
        Course course = new Course();
        course.setId(1);

        when(courseRepository.save(any(Course.class))).thenReturn(course);

        assertEquals(course,courseService.save(course));
    }
}