package com.example.course.mapper;

public class StudentMark {
    private Integer studentId;
    private Integer mark;
    private String courseName;

    public StudentMark(Integer studentId, String courseName, Integer mark) {
        this.studentId = studentId;
        this.mark = mark;
        this.courseName = courseName;
    }
}
