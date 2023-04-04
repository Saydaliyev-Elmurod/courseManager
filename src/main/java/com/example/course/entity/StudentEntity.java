package com.example.course.entity;

import com.example.course.enums.Gender;
import com.example.course.enums.Level;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "students")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    @Enumerated(EnumType.STRING)
    private Level level;
    @Column
    private Integer age;
    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column
    private LocalDateTime createdDate;
}
