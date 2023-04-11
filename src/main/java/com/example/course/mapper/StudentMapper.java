package com.example.course.mapper;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentMapper {
    private Integer id;
    private String name;
    private String phone;

    public StudentMapper() {
    }

    public StudentMapper(Integer id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }
}
