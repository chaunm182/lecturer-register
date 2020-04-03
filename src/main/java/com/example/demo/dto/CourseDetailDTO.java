package com.example.demo.dto;

public class CourseDetailDTO {
    private Integer id;
    private Integer startLesson;
    private Integer startDate;
    private Integer startWeek;
    private Integer endWeek;
    private Integer numberOfLessons;
    private String room;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStartLesson() {
        return startLesson;
    }

    public void setStartLesson(Integer startLesson) {
        this.startLesson = startLesson;
    }

    public Integer getStartDate() {
        return startDate;
    }

    public void setStartDate(Integer startDate) {
        this.startDate = startDate;
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

    public Integer getNumberOfLessons() {
        return numberOfLessons;
    }

    public void setNumberOfLessons(Integer numberOfLessons) {
        this.numberOfLessons = numberOfLessons;
    }
}
