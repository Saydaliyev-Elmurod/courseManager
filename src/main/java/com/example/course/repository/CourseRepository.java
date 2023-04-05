package com.example.course.repository;

import com.example.course.entity.CourseEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
public interface CourseRepository extends CrudRepository<CourseEntity,Integer> {
    List<CourseEntity> getByDuration(String duration);
    List<CourseEntity> getByName(String name);
    List<CourseEntity> getByPrice(Double price);

    List<CourseEntity> getByCreatedDateBetween(LocalDateTime fromDate, LocalDateTime toDate);
    List<CourseEntity> getByPriceBetween(Double fPrice, Double sPrice);
}
