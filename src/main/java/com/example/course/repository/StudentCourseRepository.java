package com.example.course.repository;

import com.example.course.entity.StudentCourseEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.yaml.snakeyaml.error.Mark;

import java.time.LocalDateTime;
import java.util.List;

public interface StudentCourseRepository extends CrudRepository<StudentCourseEntity, Integer> {
    StudentCourseEntity getTop1ByStudentIdAndCourse_IdOrderByMarkDesc(Integer s_id, Integer c_id);

    @Query(value = "select avg(mark) from student_course where student_id = :s_id", nativeQuery = true)
    Double findAvgMark(Integer s_id);

    StudentCourseEntity getTop1ByStudentIdAndCourse_IdOrderByCreatedDateAsc(Integer s_id, Integer c_id);

    StudentCourseEntity getById(Integer id);

    List<StudentCourseEntity> getTop3ByStudentIdOrderByMarkDesc(Integer id);

    StudentCourseEntity getTop1ByStudentIdOrderByCreatedDateDesc(Integer id);

    StudentCourseEntity getTop1ByStudentIdOrderByCreatedDate(Integer id);

    List<StudentCourseEntity> getByStudentIdAndCourse_IdOrderByCreatedDateDesc(Integer s_id, Integer c_id);

    List<StudentCourseEntity> getByStudentIdOrderByCreatedDateDesc(Integer id);

    List<StudentCourseEntity> getByStudentIdAndCreatedDateBetween(Integer id, LocalDateTime fDate, LocalDateTime tDate);
}
