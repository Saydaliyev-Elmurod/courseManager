package com.example.course.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.yaml.snakeyaml.error.Mark;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@Table(name = "student_course")
public class StudentCourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private StudentEntity student;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private CourseEntity course;
    @Column
    private LocalDateTime createdDate;
    @Column
    private Integer mark;

    public StudentCourseEntity(Integer id, StudentEntity student, CourseEntity course) {
        this.id = id;
        this.student = student;
        this.course = course;
    }

    public StudentCourseEntity() {

    }
}
