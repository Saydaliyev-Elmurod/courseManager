package com.example.course.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
@Getter
@Setter
@ToString
public class StudentCourseDTO {
    private Integer id;
    private Integer student_id;
    private Integer course_id;
    private LocalDateTime createdDate;
    private Integer mark;
}
