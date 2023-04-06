package com.example.course.service;

import com.example.course.dto.StudentCourseDTO;
import com.example.course.entity.CourseEntity;
import com.example.course.entity.StudentCourseEntity;
import com.example.course.entity.StudentEntity;
import com.example.course.exp.NotValidException;
import com.example.course.repository.CourseRepository;
import com.example.course.repository.StudentCourseRepository;
import com.example.course.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.error.Mark;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class StudentCourseService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentCourseRepository studentCourseRepository;

    public StudentCourseEntity create(StudentCourseDTO dto) {
        StudentEntity student = studentRepository.findById(dto.getStudent_id()).orElse(null);
        if (student == null) {
            throw new NotValidException("Student not found");
        }
        CourseEntity course = courseRepository.findById(dto.getCourse_id()).orElse(null);
        if (course == null) {
            throw new NotValidException("Course not found");
        }
        StudentCourseEntity entity = new StudentCourseEntity();
        entity.setStudent(student);
        entity.setCourse(course);
        entity.setMark(dto.getMark());
        entity.setCreatedDate(LocalDateTime.now());
        return studentCourseRepository.save(entity);
    }

    private StudentCourseDTO entityToDto(StudentCourseEntity entity) {
        StudentCourseDTO dto = new StudentCourseDTO();
        dto.setCourse_id(entity.getCourse().getId());
        dto.setStudent_id(entity.getStudent().getId());
        dto.setId(entity.getId());
        dto.setMark(entity.getMark());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;

    }

    public StudentCourseEntity update(Integer id, StudentCourseDTO dto) {
        StudentCourseEntity entity = studentCourseRepository.findById(id).orElse(null);
        if (entity == null) {
            throw new NotValidException("StudentCourse not found");
        }
        StudentEntity student = studentRepository.findById(dto.getStudent_id()).orElse(null);
        if (student == null) {
            throw new NotValidException("Student not found");
        }
        CourseEntity course = courseRepository.findById(dto.getCourse_id()).orElse(null);
        if (course == null) {
            throw new NotValidException("Course not found");
        }
        entity.setStudent(student);
        entity.setCourse(course);
        entity.setMark(dto.getMark());
        entity.setCreatedDate(LocalDateTime.now());
        entity = studentCourseRepository.save(entity);
        return entity;

    }

    public StudentCourseDTO getById(java.lang.Integer id) {
        StudentCourseEntity entity = studentCourseRepository.findById(id).orElse(null);
        if (entity == null) {
            throw new NotValidException("StudentCourse not found");
        }
        return entityToDto(entity);
    }

    public StudentCourseEntity getByIdWithDetail(Integer id) {
        StudentCourseEntity entity = studentCourseRepository.findById(id).orElse(null);
//        StudentEntity student = entity.getStudent();
//        CourseEntity course = entity.getCourse();
        if (entity == null) {
            throw new NotValidException("StudentCourse not found");
        }
        return entity;
    }

    public List<StudentCourseDTO> list() {
        List<StudentCourseDTO> dtoList = new ArrayList<>();
        studentCourseRepository.findAll().forEach(studentCourseEntity -> {
            dtoList.add(entityToDto(studentCourseEntity));
        });
        return dtoList;
    }

    public StudentCourseDTO delete(java.lang.Integer id) {
        Optional<StudentCourseEntity> entity = studentCourseRepository.findById(id);
        if (entity.isEmpty()) {
            throw new NotValidException("Student not found");
        }
        studentCourseRepository.delete(entity.get());
        return entityToDto(entity.get());
    }

    public List<Integer> getMarkDay(java.lang.Integer id, LocalDate localDate) {
        List<StudentCourseEntity> courseEntityList = studentCourseRepository.getByStudentIdAndCreatedDateBetween(id, localDate.atStartOfDay(), localDate.atStartOfDay().plusDays(1).minusSeconds(1));
        return getMarkList(courseEntityList);
    }

    public List<Integer> getMarkDayBetween(java.lang.Integer sId, LocalDate fDate, LocalDate tDate) {
        List<StudentCourseEntity> courseEntityList = studentCourseRepository.getByStudentIdAndCreatedDateBetween(sId, fDate.atStartOfDay(), tDate.atStartOfDay().plusDays(1).minusSeconds(1));
        return getMarkList(courseEntityList);
    }

    public List<Integer> getMarkAllOrderCD(java.lang.Integer sId) {
        List<StudentCourseEntity> courseEntityList = studentCourseRepository.getByStudentIdOrderByCreatedDateDesc(sId);
        return getMarkList(courseEntityList);
    }

    public List<Integer> getAllMarkCourseOrderCD(java.lang.Integer sId, java.lang.Integer c_id) {
        List<StudentCourseEntity> courseEntityList = studentCourseRepository.getByStudentIdAndCourse_IdOrderByCreatedDateDesc(sId, c_id);
        return getMarkList(courseEntityList);
    }

    private List<Integer> getMarkList(List<StudentCourseEntity> courseEntityList) {
        List<Integer> marklist = new ArrayList<>();
        courseEntityList.forEach(studentCourseEntity -> {
            marklist.add(studentCourseEntity.getMark());
        });
        return marklist;
    }

    public Object lastMark(Integer sId) {
        StudentCourseEntity entity = studentCourseRepository.getTop1ByStudentIdOrderByCreatedDateDesc(sId);
        HashMap<Integer, String> map = new HashMap<>();
        map.put(entity.getMark(), entity.getCourse().getName());
        return map;
    }

    public Object firstMark(java.lang.Integer sId) {
        StudentCourseEntity entity = studentCourseRepository.getTop1ByStudentIdOrderByCreatedDate(sId);
        HashMap<Integer, String> map = new HashMap<>();
        map.put(entity.getMark(), entity.getCourse().getName());
        return map;
    }

    public Object getTop3(java.lang.Integer sId) {
        return getMarkList(studentCourseRepository.getTop3ByStudentIdOrderByMarkDesc(sId));
    }

    public HashMap<Integer, LocalDateTime> getFirstMarkCourse(java.lang.Integer sId, java.lang.Integer cId) {
        StudentCourseEntity entity = studentCourseRepository.getTop1ByStudentIdAndCourse_IdOrderByCreatedDateAsc(sId, cId);
        HashMap<Integer, LocalDateTime> map = new HashMap<>();
        map.put(entity.getMark(), entity.getCreatedDate());
        return map;
    }

    public Object getMaxMarkCourse(java.lang.Integer sId, java.lang.Integer cId) {
        StudentCourseEntity entity = studentCourseRepository.getTop1ByStudentIdAndCourse_IdOrderByMarkDesc(sId, cId);
        HashMap<Integer, String> map = new HashMap<>();
        map.put(entity.getMark(), entity.getCourse().getName());
        return map;
    }

    public Double getAvgMark(Integer sId) {
        return studentCourseRepository.findAvgMark(sId);
    }
}
