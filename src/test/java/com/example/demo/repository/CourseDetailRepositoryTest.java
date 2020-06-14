package com.example.demo.repository;

import com.example.demo.entity.Course;
import com.example.demo.entity.CourseDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CourseDetailRepositoryTest {

    @Autowired
    CourseDetailRepository courseDetailRepository;

    @Test
    void findByCourseId(){
        List<CourseDetail> courseDetails = courseDetailRepository.findByCourseId(1);
        assertEquals(2,courseDetails.size());
    }

    @Test
    void findByCourseId1(){
        List<CourseDetail> courseDetails = courseDetailRepository.findByCourseId(999);
        assertEquals(0,courseDetails.size());
    }

    @Test
    void findById(){
        Optional<CourseDetail> courseDetails = courseDetailRepository.findById(1);
        assertTrue(courseDetails.isPresent());
    }

    @Test
    void findById1(){
        Optional<CourseDetail> courseDetails = courseDetailRepository.findById(999);
        assertFalse(courseDetails.isPresent());
    }

    @Test
    void save(){
        CourseDetail courseDetail = new CourseDetail();
        courseDetail.setCourse(new Course());
        courseDetail.setNumberOfLessons(2);
        courseDetail.setRoom("102");
        courseDetail.setStartDate(2);
        courseDetail.setStartLesson(4);
        courseDetail.setStartWeek(1);
        courseDetail.setEndWeek(15);

        assertNotNull(courseDetailRepository.save(courseDetail));
    }

    @Test
    void delete(){
        courseDetailRepository.deleteById(1);
        assertFalse(courseDetailRepository.findById(1).isPresent());
    }
}
