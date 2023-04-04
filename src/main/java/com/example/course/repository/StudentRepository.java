package com.example.course.repository;

import com.example.course.entity.StudentEntity;
import com.example.course.enums.Gender;
import com.example.course.enums.Level;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface StudentRepository extends CrudRepository<StudentEntity, Integer> {

    StudentEntity getById(Integer id);

    List<StudentEntity> getByName(String name);
    List<StudentEntity> getBySurname(String surname);
    List<StudentEntity> getByAge(Integer age);
    List<StudentEntity> getByGender(Gender gender);
    List<StudentEntity> getByLevel(Level level);
    List<StudentEntity> getByCreatedDateBetween(LocalDateTime fromDate,LocalDateTime toDate);

}
