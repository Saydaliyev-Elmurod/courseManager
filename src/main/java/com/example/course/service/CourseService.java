package com.example.course.service;

import com.example.course.dto.CourseDTO;
import com.example.course.dto.StudentDTO;
import com.example.course.entity.CourseEntity;
import com.example.course.entity.StudentEntity;
import com.example.course.exp.NotValidException;
import com.example.course.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    public CourseDTO create(CourseDTO dto) {
        /// checking
        if (dto.getName() == null ) {
            throw new NotValidException("Name is null");
        }
        /// ....
        CourseEntity entity = dtoToEntity(dto);
        CourseEntity response = courseRepository.save(entity);
        dto.setId(response.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }
    public CourseDTO getById(Integer id) {
        Optional<CourseEntity> courseEntityOptional = courseRepository.findById(id);
        if (courseEntityOptional.isEmpty()) {
            throw new NotValidException("Course not found");
        }
        return entityToDTO(courseEntityOptional.get());
    }

    public List<CourseDTO> list() {
        Iterable<CourseEntity> iterable = courseRepository.findAll();
        List<CourseDTO> studentDTOList = new ArrayList<>();
        iterable.forEach(entity -> {
                    CourseDTO courseDTO = entityToDTO(entity);
                    studentDTOList.add(courseDTO);
                }
        );
        return studentDTOList;
    }
    public CourseDTO update(Integer id, CourseDTO dto) {
        Optional<CourseEntity> optional = courseRepository.findById(id);
        if (optional.isEmpty()) {
            throw new NotValidException("Student not found");
        }
        CourseEntity entity = optional.get();
        Integer entityId = entity.getId();
        entity = dtoToEntity(dto);
        entity.setId(entityId);
        entity = courseRepository.save(entity);
        return entityToDTO(entity);
    }

    public CourseDTO delete(Integer id) {
        Optional<CourseEntity> entity = courseRepository.findById(id);
        if (entity.isEmpty()) {
            throw new NotValidException("Student not found");
        }
        courseRepository.delete(entity.get());
        return entityToDTO(entity.get());
//        studentRepository.delete(studentRepository.getById(id));
//        studentRepository.deleteById(id);
    }
    public List<CourseDTO> getByName(String name) {
        List<CourseEntity> courseEntities = courseRepository.getByName(name);
        return list(courseEntities);
    }

    public List<CourseDTO> getByDuration(String duration) {
        List<CourseEntity> courseEntities = courseRepository.getByDuration(duration);
        return list(courseEntities);
    }

    public List<CourseDTO> list(List<CourseEntity> list) {
        List<CourseDTO> courseDTOS = new ArrayList<>();
        for (CourseEntity entity : list) {
            courseDTOS.add(entityToDTO(entity));
        }
        return courseDTOS;
    }

    public List<CourseDTO> getByPrice(Double price) {
        return list(courseRepository.getByPrice(price));
    }
    public List<CourseDTO> getByBetweenGivenDate(LocalDate fromDate, LocalDate toDate) {
        LocalDateTime end = toDate.plus(1l, ChronoUnit.DAYS).atStartOfDay();
        List<CourseEntity> entities = courseRepository.getByCreatedDateBetween(fromDate.atStartOfDay(),end);
        return list(entities);
    }
    public List<CourseDTO> getByBetweenGivenPrice(Double fPrice, Double sPrice) {
        List<CourseEntity> entities = courseRepository.getByPriceBetween(fPrice,sPrice);
        return list(entities);
    }
    public CourseEntity dtoToEntity(CourseDTO dto) {
        CourseEntity entity = new CourseEntity();
        entity.setPrice(dto.getPrice());
        entity.setName(dto.getName());
        entity.setDuration(dto.getDuration());
        entity.setCreatedDate(LocalDateTime.now());
        return entity;
    }

    public CourseDTO entityToDTO(CourseEntity entity) {
        CourseDTO dto = new CourseDTO();
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setName(entity.getName());
        dto.setDuration(entity.getDuration());
        dto.setPrice(entity.getPrice());
        return dto;
    }

    public Page<CourseDTO> pagination(Integer page, Integer size) {
        Sort sort = Sort.by(Sort.Direction.ASC ,"id");
        Pageable pageable = PageRequest.of(page-1,size,sort);
        Page<CourseEntity> entityPage  = courseRepository.findAll(pageable);
        List<CourseDTO> dtoList = list(entityPage.getContent());
        return new PageImpl<>(dtoList,pageable,entityPage.getTotalElements());
    }
    public Page<CourseDTO> paginationCreatedDate(Integer page, Integer size) {
        Sort sort = Sort.by(Sort.Direction.ASC ,"createdDate");
        Pageable pageable = PageRequest.of(page-1,size,sort);
        Page<CourseEntity> entityPage  = courseRepository.findAll(pageable);
        List<CourseDTO> dtoList = list(entityPage.getContent());
        return new PageImpl<>(dtoList,pageable,entityPage.getTotalElements());
    }
    public Page<CourseDTO> paginationByPrice(Double price,Integer page, Integer size) {
        Sort sort = Sort.by(Sort.Direction.ASC ,"createdDate");
        Pageable pageable = PageRequest.of(page-1,size,sort);
        Page<CourseEntity> entityPage  = courseRepository.findAllByPrice(price,pageable);
        List<CourseDTO> dtoList = list(entityPage.getContent());
        return new PageImpl<>(dtoList,pageable,entityPage.getTotalElements());
    }
    public Page<CourseDTO> paginationByBetweenPrice(Double fromPrice,Double toPrice,Integer page, Integer size) {
        Sort sort = Sort.by(Sort.Direction.ASC ,"createdDate");
        Pageable pageable = PageRequest.of(page-1,size,sort);
        Page<CourseEntity> entityPage  = courseRepository.findAllByPriceBetween(fromPrice,toPrice,pageable);
        List<CourseDTO> dtoList = list(entityPage.getContent());
        return new PageImpl<>(dtoList,pageable,entityPage.getTotalElements());
    }
}
