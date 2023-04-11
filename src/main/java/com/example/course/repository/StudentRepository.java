package com.example.course.repository;

import com.example.course.entity.StudentEntity;
import com.example.course.enums.Gender;
import com.example.course.enums.Level;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface StudentRepository extends CrudRepository<StudentEntity, Integer>, PagingAndSortingRepository<StudentEntity, Integer> {


    //    List<StudentEntity> getByName(String name);
    @Query("from StudentEntity where name = :name")
    List<StudentEntity> getByName(@Param("name") String name);

    //    List<StudentEntity> getBySurname(String surname);
    @Query("from StudentEntity where surname = :surname")
    List<StudentEntity> getBySurname(@Param("surname") String surname);

    //    List<StudentEntity> getByAge(Integer age);
    @Query("from StudentEntity where age = :age")
    List<StudentEntity> getByAge(@Param("age") Integer age);

    //    List<StudentEntity> getByGender(Gender gender);
    @Query("from StudentEntity where gender = :gender")
    List<StudentEntity> getByGender(Gender gender);

    //    List<StudentEntity> getByLevel(Level level);
    @Query("from StudentEntity where level = :level")
    List<StudentEntity> getByLevel(@Param("level") Level level);

    //    List<StudentEntity> getByCreatedDateBetween(LocalDateTime fromDate, LocalDateTime toDate);
    @Query("from StudentEntity where createdDate >:fromDate and createdDate<:toDate")
    List<StudentEntity> getByCreatedDateBetween(LocalDateTime fromDate, LocalDateTime toDate);

    Page<StudentEntity> findAllByName(String name, Pageable pageable);

}
