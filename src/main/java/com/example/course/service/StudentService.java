package com.example.course.service;

import com.example.course.dto.StudentDTO;
import com.example.course.entity.StudentEntity;
import com.example.course.enums.Gender;
import com.example.course.enums.Level;
import com.example.course.exp.NotValidException;
import com.example.course.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public StudentDTO create(StudentDTO dto) {
        /// checking
        if (dto.getName() == null || dto.getSurname() == null) {
            throw new NotValidException("Name is null");
        }
        /// ....
        StudentEntity entity = dtoToEntity(dto);
        StudentEntity student = studentRepository.save(entity);
        dto.setId(student.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    public List<StudentDTO> list() {
        Iterable<StudentEntity> iterable = studentRepository.findAll();
        List<StudentDTO> studentDTOList = new ArrayList<>();
        iterable.forEach(entity -> {
                    StudentDTO studentDTO = entityToDTO(entity);
                    studentDTOList.add(studentDTO);
                }
        );
        return studentDTOList;
    }

    public StudentDTO getById(Integer id) {
        Optional<StudentEntity> studentEntityOptional = studentRepository.findById(id);
        if (studentEntityOptional.isEmpty()) {
            throw new NotValidException("Student not found");
        }
        return entityToDTO(studentEntityOptional.get());
    }

    public StudentEntity dtoToEntity(StudentDTO dto) {
        StudentEntity entity = new StudentEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setAge(dto.getAge());
        entity.setGender(dto.getGender());
        entity.setLevel(dto.getLevel());
        entity.setCreatedDate(LocalDateTime.now());
        return entity;
    }

    public StudentDTO entityToDTO(StudentEntity entity) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setName(entity.getName());
        studentDTO.setSurname(entity.getSurname());
        studentDTO.setAge(entity.getAge());
        studentDTO.setGender(entity.getGender());
        studentDTO.setLevel(entity.getLevel());
        studentDTO.setCreatedDate(entity.getCreatedDate());
        studentDTO.setId(entity.getId());
        return studentDTO;
    }

    public StudentDTO update(Integer id, StudentDTO dto) {
        Optional<StudentEntity> optional = studentRepository.findById(id);
        if (optional.isEmpty()) {
            throw new NotValidException("Student not found");
        }
        StudentEntity entity = optional.get();
        Integer entityId = entity.getId();
        entity = dtoToEntity(dto);
        entity.setId(entityId);
        entity = studentRepository.save(entity);
        return entityToDTO(entity);
    }

    public StudentDTO delete(Integer id) {
        Optional<StudentEntity> entity = studentRepository.findById(id);
        if (entity.isEmpty()) {
            throw new NotValidException("Student not found");
        }
        studentRepository.delete(entity.get());
        return entityToDTO(entity.get());
//        studentRepository.delete(studentRepository.getById(id));
//        studentRepository.deleteById(id);
    }

    public List<StudentDTO> getByName(String name) {
        List<StudentEntity> studentEntityList = studentRepository.getByName(name);
        return list(studentEntityList);
    }

    public List<StudentDTO> getBySurname(String surname) {
        List<StudentEntity> studentEntityList = studentRepository.getBySurname(surname);
        return list(studentEntityList);
    }

    public List<StudentDTO> list(List<StudentEntity> list) {
        List<StudentDTO> studentDTOList = new ArrayList<>();
        for (StudentEntity entity : list) {
            studentDTOList.add(entityToDTO(entity));
        }
        return studentDTOList;
    }

    public List<StudentDTO> getByAge(Integer age) {
        return list(studentRepository.getByAge(age));
    }

    public List<StudentDTO> getByLevel(Level level) {
        return list(studentRepository.getByLevel(level));
    }

    public List<StudentDTO> getByGender(Gender gender) {
        return list(studentRepository.getByGender(gender));
    }

    public List<StudentDTO> getByDate(LocalDate localDate) {
        LocalDateTime end = localDate.plus(1l, ChronoUnit.DAYS).atStartOfDay();
        List<StudentEntity> entities = studentRepository.getByCreatedDateBetween(localDate.atStartOfDay(), end);
        return list(entities);
    }

    public List<StudentDTO> getByBetweenGivenDate(LocalDate fromDate, LocalDate toDate) {
        LocalDateTime end = toDate.plus(1l, ChronoUnit.DAYS).atStartOfDay();
        List<StudentEntity> entities = studentRepository.getByCreatedDateBetween(fromDate.atStartOfDay(), end);
        return list(entities);
    }

    public Page<StudentDTO> paginationWithName(String name, int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable paging = PageRequest.of(page - 1, size, sort);
        Page<StudentEntity> pageObj = studentRepository.findAllByName(name, paging);

        Long totalCount = pageObj.getTotalElements();
        List<StudentEntity> entityList = pageObj.getContent();
        List<StudentDTO> dtoList = new LinkedList<>();
        for (StudentEntity entity : entityList) {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dtoList.add(dto);
        }
        Page<StudentDTO> response = new PageImpl<StudentDTO>(dtoList, paging, totalCount);
        return response;
    }

    public Page<StudentDTO> pagination(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
//        Iterable<StudentEntity> studentEntityIterable = studentRepository.findAll(sort);
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<StudentEntity> pageObj = studentRepository.findAll(pageable);
        Long totalCount = pageObj.getTotalElements();
        List<StudentEntity> entitiList = pageObj.getContent();
        List<StudentDTO> dtoList = new ArrayList<>();
        for (StudentEntity entity : entitiList) {
            StudentDTO dto = new StudentDTO();
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setAge(entity.getId());
            dtoList.add(dto);
        }
        Page<StudentDTO> response = new PageImpl<StudentDTO>(dtoList, pageable, totalCount);
        return response;
    }

    public Page<StudentDTO> pagingByLevel(Level level, Integer page, Integer size) {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(page-1, size, sort);
        Page<StudentEntity> pageObj = studentRepository.findAllByLevel(level,pageable);
        List<StudentEntity> studentEntityList = pageObj.getContent();
        List<StudentDTO> dtoList = list(studentEntityList);
        Page<StudentDTO> response = new PageImpl<>(dtoList, pageable, pageObj.getTotalElements());
        return response;
    }

    public Page<StudentDTO> pagingByGender(Gender gender, Integer page, Integer size) {
        Sort sort = Sort.by(Sort.Direction.ASC,"createdDate");
        Pageable pageable = PageRequest.of(page-1,size,sort);
        Page<StudentEntity> pageObj = studentRepository.findAllByGender(gender,pageable);
        List<StudentEntity> entityList = pageObj.getContent();
        Long totalElement = pageObj.getTotalElements();
        List<StudentDTO> dtoList = list(entityList);
        return new PageImpl<>(dtoList,pageable,totalElement);
    }
}
