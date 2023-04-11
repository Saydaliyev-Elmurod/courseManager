package com.example.course.controller;

import com.example.course.dto.StudentCourseDTO;
import com.example.course.dto.StudentDTO;
import com.example.course.service.StudentCourseService;
import com.example.course.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/student_course")
public class StudentCourseController {
    @Autowired
    private StudentCourseService service;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody StudentCourseDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id,
                                    @RequestBody StudentCourseDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @GetMapping("/list")
    public ResponseEntity<?> list() {
        return ResponseEntity.ok(service.list());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/getWithDetail/{id}")
    public ResponseEntity<?> getByIdWithDetail(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getByIdWithDetail(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(service.delete(id));
    }

    @GetMapping("/getMark")
    public ResponseEntity<?> getMarkDay(@RequestParam("s_id") Integer s_id,
                                        @RequestParam("date") LocalDate date) {
        return ResponseEntity.ok(service.getMarkDay(s_id, date));
    }

    @GetMapping("/getMarkBetweenDays")
    public ResponseEntity<?> getMarkBetweenDays(@RequestParam("s_id") Integer s_id,
                                                @RequestParam("f_date") LocalDate f_date,
                                                @RequestParam("t_date") LocalDate t_date) {
        return ResponseEntity.ok(service.getMarkDayBetween(s_id, f_date, t_date));
    }

    @GetMapping("/getMarkAll")
    public ResponseEntity<?> getMarkAllOrderCD(@RequestParam("s_id") Integer s_id) {
        return ResponseEntity.ok(service.getMarkAllOrderCD(s_id));
    }

    @GetMapping("/getAllMarkCourse")
    public ResponseEntity<?> getAllMarkCourseOrderCD(@RequestParam("s_id") Integer s_id,
                                                     @RequestParam("c_id") Integer c_id) {
        return ResponseEntity.ok(service.getAllMarkCourseOrderCD(s_id, c_id));
    }

    @GetMapping("/lastMark")
    public ResponseEntity<?> getLastMark(@RequestParam("s_id") Integer s_id) {
        return ResponseEntity.ok(service.lastMark(s_id));
    }
    @GetMapping("/firstMark")
    public ResponseEntity<?> getFirstMark(@RequestParam("s_id") Integer s_id) {
        return ResponseEntity.ok(service.firstMark(s_id));
    }
    @GetMapping("/top3")
    public ResponseEntity<?> getTop3(@RequestParam("s_id") Integer s_id) {
        return ResponseEntity.ok(service.getTop3(s_id));
    }
    @GetMapping("/firstMarkCourse")
    public ResponseEntity<?> getFirstMarkCourse(@RequestParam("s_id") Integer s_id,
                                                @RequestParam ("c_id") Integer c_id) {
        return ResponseEntity.ok(service.getFirstMarkCourse(s_id,c_id));
    }
    @GetMapping("/maxMarkCourse")
    public ResponseEntity<?> getMaxMarkCourse(@RequestParam("s_id") Integer s_id,
                                              @RequestParam("c_id") Integer c_id){
        return ResponseEntity.ok(service.getMaxMarkCourse(s_id,c_id));
    }
    @GetMapping("/avgMark")
    public ResponseEntity<?> getAvgMark(@RequestParam("s_id") Integer s_id){
        return ResponseEntity.ok(service.getAvgMark(s_id));
    }
    @GetMapping("/avgMarkCourse")
    public ResponseEntity<?> getAvgMarkCourse(@RequestParam("s_id") Integer s_id,
                                              @RequestParam("c_id") Integer c_id){
        return ResponseEntity.ok(service.getAvgMarkCourse(s_id,c_id));
    }
    @GetMapping("/getGreatCountMark")
    public ResponseEntity<?> getGreatCountMark(@RequestParam("s_id") Integer s_id,
                                              @RequestParam("mark") Integer mark){
        return ResponseEntity.ok(service.getGreatCountMark(s_id,mark));
    }
    @GetMapping("/getCountMarkCourse")
    public ResponseEntity<?> getCountMarkCourse(@RequestParam("s_id") Integer s_id,
                                               @RequestParam("c_id") Integer c_id){
        return ResponseEntity.ok(service.getCountMarkCourse(s_id,c_id));
    }
}
