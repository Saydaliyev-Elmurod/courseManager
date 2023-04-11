package com.example.course.repository;

import com.example.course.entity.CourseEntity;
import com.example.course.mapper.CourseInfoMapper;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.util.List;

public interface CourseRepository extends CrudRepository<CourseEntity, Integer> {
    //    List<CourseEntity> getByDuration(String duration);
    @Query("from CourseEntity  where duration=:duration")
    List<CourseEntity> getByDuration(String duration);

    //    List<CourseEntity> getByName(String name);
    @Query("from CourseEntity  where name=:name")
    List<CourseEntity> getByName(String name);

    //    List<CourseEntity> getByPrice(Double price);
    @Query("from CourseEntity  where price=:price")
    List<CourseEntity> getByPrice(Double price);

    //    List<CourseEntity> getByCreatedDateBetween(LocalDateTime fromDate, LocalDateTime toDate);
    @Query("from CourseEntity  where createdDate>:fromDate and createdDate<:toDate")
    List<CourseEntity> getByCreatedDateBetween(LocalDateTime fromDate, LocalDateTime toDate);

    //    List<CourseEntity> getByPriceBetween(Double fPrice, Double sPrice);
    @Query("from CourseEntity  where price>:fPrice and price<:sPrice")
    List<CourseEntity> getByPriceBetween(Double fPrice, Double sPrice);
    @Query(value = "SELECT course_id from  student_course_mark where student_id = :studentId order by created_date desc limit 1 ", nativeQuery = true)
    Integer findLastCourseMarker(@Param("studentId") Integer studentId);

}
