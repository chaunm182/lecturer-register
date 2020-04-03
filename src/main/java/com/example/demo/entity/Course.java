package com.example.demo.entity;


import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "tbl_course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "semester")
    private Integer semester;

    @Column(name = "course_id")
    private String courseId;

    @Column(name = "registered_at")
    private Date registeredAt;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @OneToMany(mappedBy = "course",cascade = {CascadeType.REMOVE})
    private Set<CourseDetail> courseDetails;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Lecturer.class)
    @JoinColumn(name = "lecturer_id")
    private Lecturer lecturer;

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

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Set<CourseDetail> getCourseDetails() {
        return courseDetails;
    }

    public void setCourseDetails(Set<CourseDetail> courseDetails) {
        this.courseDetails = courseDetails;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }


}
