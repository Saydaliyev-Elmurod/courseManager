package com.example.course.controller;

import com.example.course.dto.CourseDTO;
import com.example.course.dto.StudentDTO;
import com.example.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CourseDTO dto) {
        CourseDTO response = courseService.create(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        CourseDTO courseDTO = courseService.getById(id);
        return ResponseEntity.ok(courseDTO);
    }

    @GetMapping("/list")
    public ResponseEntity<?> list() {
        List<CourseDTO> courseDTOS = courseService.list();
        return ResponseEntity.ok(courseDTOS);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id,
                                    @RequestBody CourseDTO dto) {
        CourseDTO courseDTO = courseService.update(id, dto);
        return ResponseEntity.ok(courseDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(courseService.delete(id));
    }

    @GetMapping("/getByName/{name}")
    public ResponseEntity<?> getByName(@PathVariable String name) {
        return ResponseEntity.ok(courseService.getByName(name));
    }

    @GetMapping("/getByPrice/{price}")
    public ResponseEntity<?> getBySurname(@PathVariable Double price) {
        return ResponseEntity.ok(courseService.getByPrice(price));
    }

    @GetMapping("/getDuration/{duration}")
    public ResponseEntity<?> getByAge(@PathVariable String duration) {
        return ResponseEntity.ok(courseService.getByDuration(duration));
    }

    @GetMapping("/getByBetweenGivenDate")
    public ResponseEntity<?> getByBetweenGivenDate(@RequestParam("fromDate") LocalDate fromDate,
                                                   @RequestParam("toDate") LocalDate toDate) {
        return ResponseEntity.ok(courseService.getByBetweenGivenDate(fromDate, toDate));
    }

    @GetMapping("/getByBetweenGivenPrice")
    public ResponseEntity<?> getByBetweenGivenPrice(@RequestParam("fPrice") Double fPrice,
                                                    @RequestParam("sPrice") Double sPrice) {
        return ResponseEntity.ok(courseService.getByBetweenGivenPrice(fPrice, sPrice));
    }

    @GetMapping("/pagination")
    public ResponseEntity<?> pagination(@RequestParam("page") Integer page,
                                        @RequestParam("size") Integer size) {
        Page<CourseDTO> response = courseService.pagination(page, size);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/paginationCreatedDate")
    public ResponseEntity<?> paginationCreatedDate(@RequestParam("page") Integer page,
                                                   @RequestParam("size") Integer size) {
        Page<CourseDTO> response = courseService.pagination(page, size);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/paginationByPrice")
    public ResponseEntity<?> paginationByPrice(@RequestParam("price") Double price,
                                               @RequestParam("page") Integer page,
                                               @RequestParam("size") Integer size) {
        Page<CourseDTO> response = courseService.paginationByPrice(price, page, size);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/paginationByBetweenPrice")
    public ResponseEntity<?> paginationByBetweenPrice(@RequestParam("fPrice") Double fPrice,
                                                      @RequestParam("tPrice") Double toPrice,
                                                      @RequestParam("page") Integer page,
                                                      @RequestParam("size") Integer size) {
        Page<CourseDTO> response = courseService.paginationByBetweenPrice(fPrice,toPrice, page, size);
        return ResponseEntity.ok(response);
    }

}
