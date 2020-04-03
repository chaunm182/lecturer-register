package com.example.demo.dto;

import java.util.Date;
import java.util.Set;

public class CourseDTO {
    private Integer id;
    private Integer semester;
    private String courseId;
    private Date registeredAt;
    private SubjectDTO subjectDTO;
    private LectureDTO lectureDTO;
    private Set<CourseDetailDTO> courseDetailDTOS;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public Date getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(Date registeredAt) {
        this.registeredAt = registeredAt;
    }

    public SubjectDTO getSubjectDTO() {
        return subjectDTO;
    }

    public void setSubjectDTO(SubjectDTO subjectDTO) {
        this.subjectDTO = subjectDTO;
    }

    public LectureDTO getLectureDTO() {
        return lectureDTO;
    }

    public void setLectureDTO(LectureDTO lectureDTO) {
        this.lectureDTO = lectureDTO;
    }

    public Set<CourseDetailDTO> getCourseDetailDTOS() {
        return courseDetailDTOS;
    }

    public void setCourseDetailDTOS(Set<CourseDetailDTO> courseDetailDTOS) {
        this.courseDetailDTOS = courseDetailDTOS;
    }
}
