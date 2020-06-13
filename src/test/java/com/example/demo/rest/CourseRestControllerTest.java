package com.example.demo.rest;

import com.example.demo.dto.CourseDTO;
import com.example.demo.entity.Course;
import com.example.demo.entity.CourseDetail;
import com.example.demo.entity.Subject;
import com.example.demo.service.CourseService;
import com.example.demo.util.bean.CourseBeanUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class CourseRestControllerTest {

    @Autowired
    CourseBeanUtil courseBeanUtil1;

    @Mock
    CourseService courseService;

    @Mock
    CourseBeanUtil courseBeanUtil;

    @InjectMocks
    CourseRestController courseRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    void findCoursesBySubjectId() {
        Course course  = new Course();
        course.setId(1);
        Subject subject = new Subject();
        subject.setId(1);
        course.setSubject(subject);
        Set<CourseDetail> courseDetails = new HashSet<>();
        course.setCourseDetails(courseDetails);

        Set<Course> courses = new HashSet<>();
        courses.add(course);

        CourseDTO courseDTO = courseBeanUtil1.entity2DTO(course);

        when(courseService.findCoursesBySubjectId(anyString())).thenReturn(courses);
        when(courseBeanUtil.entity2DTO(any(Course.class))).thenReturn(courseDTO);

        assertEquals(courses.size(),courseRestController.findCoursesBySubjectId(anyString()).size());
    }

    @Test
    void findCoursesByLecturerId() {
        Course course  = new Course();
        course.setId(1);
        Subject subject = new Subject();
        subject.setId(1);
        course.setSubject(subject);
        Set<CourseDetail> courseDetails = new HashSet<>();
        course.setCourseDetails(courseDetails);

        Set<Course> courses = new HashSet<>();
        courses.add(course);

        CourseDTO courseDTO = courseBeanUtil1.entity2DTO(course);

        when(courseService.findCoursesByLecturerId(anyInt())).thenReturn(courses);
        when(courseBeanUtil.entity2DTO(any(Course.class))).thenReturn(courseDTO);

        assertEquals(courses.size(),courseRestController.findCoursesByLecturerId(anyInt()).size());
    }

    @Test
    void findByIdWhenCourseIsNull() {
        when(courseService.findById(anyInt())).thenReturn(null);
        assertNull(courseRestController.findById(anyInt()));
    }

    @Test
    void findByIdWhenCourseIsNotNull() {
        Course course  = new Course();
        course.setId(1);
        Subject subject = new Subject();
        subject.setId(1);
        course.setSubject(subject);
        Set<CourseDetail> courseDetails = new HashSet<>();
        course.setCourseDetails(courseDetails);

        CourseDTO courseDTO = courseBeanUtil1.entity2DTO(course);

        when(courseService.findById(anyInt())).thenReturn(course);
        when(courseBeanUtil.entity2DTO(course)).thenReturn(courseDTO);
        CourseDTO courseDTOResult = courseBeanUtil.entity2DTO(course);

        assertEquals(courseDTOResult,courseRestController.findById(anyInt()));
    }

    @Test
    void registerCourseWhenNoCoursesContainRegisteredAt() {
        Course course  = new Course();
        course.setId(1);
        Subject subject = new Subject();
        subject.setId(1);
        course.setSubject(subject);
        Set<CourseDetail> courseDetails = new HashSet<>();
        course.setCourseDetails(courseDetails);

        Course course1  = new Course();
        course1.setId(2);
        Subject subject1 = new Subject();
        subject1.setId(2);
        course1.setSubject(subject1);
        Set<CourseDetail> courseDetails1 = new HashSet<>();
        course1.setCourseDetails(courseDetails1);

        when(courseService.findById(1)).thenReturn(course);
        when(courseService.findById(2)).thenReturn(course1);
        when(courseService.save(course)).thenReturn(course);
        when(courseService.save(course1)).thenReturn(course1);

        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);

        assertEquals(2, courseRestController.registerCourse(anyInt(),ids));
    }

    @Test
    void registerCourseWhenACourseContainRegisteredAt() {
        Course course  = new Course();
        course.setId(1);
        Subject subject = new Subject();
        subject.setId(1);
        course.setSubject(subject);
        Set<CourseDetail> courseDetails = new HashSet<>();
        course.setCourseDetails(courseDetails);

        Course course1  = new Course();
        course1.setId(2);
        course1.setRegisteredAt(new Date());
        Subject subject1 = new Subject();
        subject1.setId(2);
        course1.setSubject(subject1);
        Set<CourseDetail> courseDetails1 = new HashSet<>();
        course1.setCourseDetails(courseDetails1);

        when(courseService.findById(1)).thenReturn(course);
        when(courseService.findById(2)).thenReturn(course1);
        when(courseService.save(course)).thenReturn(course);
        when(courseService.save(course1)).thenReturn(course1);

        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);

        assertEquals(1, courseRestController.registerCourse(anyInt(),ids));
    }

    @Test
    void registerCourseWhenListCoursesIsEmpty() {
        List<Integer> ids = new ArrayList<>();
        assertEquals(0, courseRestController.registerCourse(anyInt(),ids));
    }

    @Test
    void cancelRegistrationWhenListCoursesIsEmpty() {
        List<Integer> ids = new ArrayList<>();
        assertEquals(0, courseRestController.cancelRegistration(anyInt(),ids));
    }

    @Test
    void cancelRegistration(){
        Course course  = new Course();
        course.setId(1);
        Subject subject = new Subject();
        subject.setId(1);
        course.setSubject(subject);
        Set<CourseDetail> courseDetails = new HashSet<>();
        course.setCourseDetails(courseDetails);

        Course course1  = new Course();
        course1.setId(2);
        course1.setRegisteredAt(new Date());
        Subject subject1 = new Subject();
        subject1.setId(2);
        course1.setSubject(subject1);
        Set<CourseDetail> courseDetails1 = new HashSet<>();
        course1.setCourseDetails(courseDetails1);

        when(courseService.findById(1)).thenReturn(course);
        when(courseService.findById(2)).thenReturn(course1);
        when(courseService.save(course)).thenReturn(course);
        when(courseService.save(course1)).thenReturn(course1);

        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);

        assertEquals(2,courseRestController.cancelRegistration(anyInt(),ids));
    }
}