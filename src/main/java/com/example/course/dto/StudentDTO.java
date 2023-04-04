package com.example.course.dto;

import com.example.course.enums.Gender;
import com.example.course.enums.Level;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class StudentDTO {
    private Integer id;
    private String name;
    private String surname;
    private Gender gender;
    private Level level;
    private Integer age;
    private LocalDateTime createdDate;

}
