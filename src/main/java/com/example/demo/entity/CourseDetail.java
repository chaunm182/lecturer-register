package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "tbl_course_detail")
public class CourseDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "start_date")
    private Integer startDate;

    @Column(name = "start_lesson")
    private Integer startLesson;

    @Column(name = "number_of_lesson")
    private Integer numberOfLessons;

    @Column(name = "start_week")
    private Integer startWeek;

    @Column(name = "end_week")
    private Integer endWeek;

    @Column(name = "room")
    private String room;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStartDate() {
        return startDate;
    }

    public void setStartDate(Integer startDate) {
        this.startDate = startDate;
    }

    public Integer getStartLesson() {
        return startLesson;
    }

    public void setStartLesson(Integer startLesson) {
        this.startLesson = startLesson;
    }

    public Integer getNumberOfLessons() {
        return numberOfLessons;
    }

    public void setNumberOfLessons(Integer numberOfLessons) {
        this.numberOfLessons = numberOfLessons;
    }

    public Integer getStartWeek() {
        return startWeek;
    }

    public void setStartWeek(Integer startWeek) {
        this.startWeek = startWeek;
    }

    public Integer getEndWeek() {
        return endWeek;
    }

    public void setEndWeek(Integer endWeek) {
        this.endWeek = endWeek;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }


}
