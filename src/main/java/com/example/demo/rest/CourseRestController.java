package com.example.demo.rest;

import com.example.demo.dto.CourseDTO;
import com.example.demo.entity.Course;
import com.example.demo.entity.Lecturer;
import com.example.demo.service.CourseService;
import com.example.demo.util.bean.CourseBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/courses")
public class CourseRestController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseBeanUtil courseBeanUtil;

    private Logger logger = Logger.getLogger(getClass().getName());

    @GetMapping("/subjects/{subjectId}")
    public Set<CourseDTO> findCoursesBySubjectId(@PathVariable("subjectId")String subjectId){
        Set<Course> courses = courseService.findCoursesBySubjectId(subjectId.trim().toUpperCase());
        Set<CourseDTO> courseDTOS = new LinkedHashSet<>();
        for(Course course: courses){
            CourseDTO courseDTO = courseBeanUtil.entity2DTO(course);
            courseDTOS.add(courseDTO);
        }
        return courseDTOS;
    }

    @GetMapping("/lecturers/{lecturerId}")
    public Set<CourseDTO> findCoursesByLecturerId(@PathVariable("lecturerId")Integer lecturerId){
        Set<Course> courses = courseService.findCoursesByLecturerId(lecturerId);
        Set<CourseDTO> courseDTOS = new LinkedHashSet<>();
        for(Course course: courses){
            CourseDTO courseDTO = courseBeanUtil.entity2DTO(course);
            courseDTOS.add(courseDTO);
        }
        return courseDTOS;
    }

    @GetMapping("/{id}")
    public CourseDTO findById(@PathVariable("id") Integer id){
        Course course = courseService.findById(id);
        if(course==null) return null;
        CourseDTO courseDTO = courseBeanUtil.entity2DTO(course);
        return courseDTO;
    }

    @PostMapping("/{lecturerId}")
    public Integer registerCourse(@PathVariable("lecturerId") Integer lecturerId,
                                  @RequestBody List<Integer> ids){
        Integer result = 0;
        for(Integer courseId : ids){
            Course course = courseService.findById(courseId);
            if(course.getRegisteredAt()==null){
                Lecturer lecturer = new Lecturer();
                lecturer.setId(lecturerId);
                course.setLecturer(lecturer);
                course.setRegisteredAt(new Date(System.currentTimeMillis()));
                courseService.save(course);
                result++;
            }
        }
        return result;
    }

    @PutMapping("/{lecturerId}")
    public Integer cancelRegistration(@PathVariable("lecturerId") Integer lecturerId,
                                      @RequestBody List<Integer> courseIds){
        Integer result = 0;
        for(Integer courseId : courseIds){
            Course course = courseService.findById(courseId);
            course.setLecturer(null);
            course.setRegisteredAt(null);
            courseService.save(course);
            result++;
        }
        return result;
    }
}
