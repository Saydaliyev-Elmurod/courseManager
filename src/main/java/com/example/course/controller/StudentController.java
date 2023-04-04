package com.example.course.controller;

import com.example.course.dto.StudentDTO;
import com.example.course.enums.Gender;
import com.example.course.enums.Level;
import com.example.course.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody StudentDTO dto) {
        StudentDTO response = studentService.create(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/list")
    public ResponseEntity<?> list() {
        List<StudentDTO> studentDTOList = studentService.list();
        return ResponseEntity.ok(studentDTOList);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        StudentDTO studentDTO = studentService.getById(id);
        return ResponseEntity.ok(studentDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id,
                                    @RequestBody StudentDTO dto) {
        StudentDTO studentDTO = studentService.update(id, dto);
        return ResponseEntity.ok(studentDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(studentService.delete(id));
    }

    @GetMapping("/getByName/{name}")
    public ResponseEntity<?> getByName(@PathVariable String name) {
        return ResponseEntity.ok(studentService.getByName(name));
    }
    @GetMapping("/getBySurname/{surname}")
    public ResponseEntity<?> getBySurname(@PathVariable String surname) {
        return ResponseEntity.ok(studentService.getBySurname(surname));
    }
    @GetMapping("/getByAge/{age}")
    public ResponseEntity<?> getByAge(@PathVariable Integer age) {
        return ResponseEntity.ok(studentService.getByAge(age));
    }
    @GetMapping("/getByLevel/{level}")
    public ResponseEntity<?> getByAge(@PathVariable Level level) {
        return ResponseEntity.ok(studentService.getByLevel(level));
    }
    @GetMapping("/getByGender/{gender}")
    public ResponseEntity<?> getByAge(@PathVariable Gender gender) {
        return ResponseEntity.ok(studentService.getByGender(gender));
    }
    @GetMapping("/getByDate")
    public ResponseEntity<?> getByDate(@RequestParam("fromDate") LocalDate localDate){
        return ResponseEntity.ok(studentService.getByDate(localDate));
    }
    @GetMapping("/getByBetweenGivenDate")
    public ResponseEntity<?> getByBetweenGivenDate(@RequestParam("fromDate") LocalDate fromDate,
                                              @RequestParam("toDate") LocalDate toDate){
        return ResponseEntity.ok(studentService.getByBetweenGivenDate(fromDate,toDate));
    }
}
