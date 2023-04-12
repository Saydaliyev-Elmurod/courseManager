package com.example.course.repository;

import com.example.course.entity.StudentCourseEntity;
import com.example.course.mapper.CourseInfoMapper;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface StudentCourseRepository extends PagingAndSortingRepository<StudentCourseEntity, Integer>, CrudRepository<StudentCourseEntity, Integer> {
    StudentCourseEntity getTop1ByStudentIdAndCourse_IdOrderByMarkDesc(Integer s_id, Integer c_id);
//    @Query(value = "SELECT scm.student_id as sId, scm.mark as mark, " +
//            "  c.id as cId, c.name as cName " +
//            " from  student_course as scm " +
//            " inner join course_t as c on c.id = scm.course_id " +
//            " where scm.student_id = :studentId  " +
//            "order by scm.created_date desc limit 1 ", nativeQuery = true)
//    CourseInfoMapper getTop1ByStudentIdAndCourse_IdOrderByMarkDesc(@Param("studentId") Integer studentId);

    @Query(value = "select avg(mark) from student_course where student_id = :s_id", nativeQuery = true)
    Double findAvgMark(Integer s_id);

    StudentCourseEntity getTop1ByStudentIdAndCourse_IdOrderByCreatedDateAsc(Integer s_id, Integer c_id);
//    @Query(value = "SELECT scm.student_id as sId, scm.mark as mark, " +
//            "  c.id as cId, c.name as cName " +
//            " from  student_course as scm " +
//            " inner join course as c on c.id = scm.course_id " +
//            " where scm.student_id = :studentId " +
//            " and scm.course_id = :course_id " +
//            "order by scm.created_date asc limit 1 ", nativeQuery = true)
//    CourseInfoMapper getTop1ByStudentIdAndCourse_IdOrderByCreatedDateAsc(@Param("studentId") Integer studentId, @Param("courseId") Integer cId);

    StudentCourseEntity getById(Integer id);

    List<StudentCourseEntity> getTop3ByStudentIdOrderByMarkDesc(Integer id);

    StudentCourseEntity getTop1ByStudentIdOrderByCreatedDateDesc(Integer id);

    StudentCourseEntity getTop1ByStudentIdOrderByCreatedDate(Integer id);

    List<StudentCourseEntity> getByStudentIdAndCourse_IdOrderByCreatedDateDesc(Integer s_id, Integer c_id);

    List<StudentCourseEntity> getByStudentIdOrderByCreatedDateDesc(Integer id);

    List<StudentCourseEntity> getByStudentIdAndCreatedDateBetween(Integer id, LocalDateTime fDate, LocalDateTime tDate);

    @Transactional
    @Modifying
    @Query("update StudentCourseEntity set mark = :mark where student.id = :sid")
    Integer updateMark(@Param("sid") Integer sId, @Param("mark") Integer mark);

    @Query("from StudentCourseEntity where mark=?1")
    List<StudentCourseEntity> markList(Integer mark);

    @Query("select new StudentCourseEntity(id,student,course) from StudentCourseEntity  where mark=?1")
    List<StudentCourseEntity> getStudentCourse(Integer mark);

    //    @Query("SELECT new com.example.course.mapper.StudentMapper(id,name,phone) FROM StudentCourseEntity ")
//    List<StudentMapper> findByName5();
    List<StudentCourseEntity> findAllByMarkIn(List<String> markList);

    @Query(value = "SELECT c.id, c.name " +
            " from  student_course_mark as scm " +
            " inner join course_t as c on c.id = scm.course_id " +
            " where scm.student_id = :studentId  " +
            "order by scm.created_date desc limit 1 ", nativeQuery = true)
    List<Object[]> findLastCourseMarkerAsNative(@Param("studentId") Integer studentId);

    @Query(value = "SELECT scm.student_id as sId, scm.mark as mark, " +
            "  c.id as cId, c.name as cName " +
            " from  student_course_mark as scm " +
            " inner join course_t as c on c.id = scm.course_id " +
            " where scm.student_id = :studentId  " +
            "order by scm.created_date desc limit 1 ", nativeQuery = true)
    CourseInfoMapper findLastCourseMarkerAsNativeMapping(@Param("studentId") Integer studentId);

    @Query("select  avg(mark) from StudentCourseEntity where student.id = :sId and course.id = :cId")
    Object getAvgMarkCourse(Integer sId, Integer cId);

    @Query("select count(mark) from StudentCourseEntity  where student.id=:sId and mark>:mark")
    Object getGreatCountMark(@Param("sId") Integer sId, @Param("mark") Integer mark);

    @Query("select count(mark) from StudentCourseEntity  where student.id=:sId and course.id=:cId")
    Object getCountMarkCourse(@Param("sId") Integer sId, @Param("cId") Integer cId);

    Page<StudentCourseEntity> findAllByStudentId(Integer sid, Pageable pageable);

    Page<StudentCourseEntity> findAllByCourseId(Integer cId, Pageable pageable);
}
