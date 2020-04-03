package com.example.demo.util.bean;

import com.example.demo.dto.CourseDTO;
import com.example.demo.dto.CourseDetailDTO;
import com.example.demo.dto.SubjectDTO;
import com.example.demo.entity.Course;
import com.example.demo.entity.CourseDetail;
import com.example.demo.entity.Subject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

@Component
public class CourseBeanUtil {

    @Autowired
    private ModelMapper modelMapper;

    public CourseDTO entity2DTO(Course course){
        CourseDTO courseDTO = modelMapper.map(course,CourseDTO.class);
        //set subject
        Subject subject = course.getSubject();
        courseDTO.setSubjectDTO(modelMapper.map(subject, SubjectDTO.class));
        //set course detail
        Set<CourseDetail> courseDetails = course.getCourseDetails();
        Set<CourseDetailDTO> courseDetailDTOS = new LinkedHashSet<>();
        for(CourseDetail courseDetail: courseDetails){
            courseDetailDTOS.add(modelMapper.map(courseDetail,CourseDetailDTO.class));
        }
        courseDTO.setCourseDetailDTOS(courseDetailDTOS);
        return courseDTO;
    }
}
