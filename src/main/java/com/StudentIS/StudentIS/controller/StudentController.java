package com.StudentIS.StudentIS.controller;

import com.StudentIS.StudentIS.entity.Student;
import com.StudentIS.StudentIS.response.ErrorResponse;
import com.StudentIS.StudentIS.service.StudentService;
import com.StudentIS.StudentIS.dto.Studentdto;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"},
        allowedHeaders = "*",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE},
        allowCredentials = "true")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Object> createStudent(@ModelAttribute @Valid Studentdto studentDTO) {
        try {
            log.info("Received student creation request for: {}", studentDTO.getStudentName());
            Student created = studentService.createStudent(studentDTO);
            return ResponseEntity.ok(created);
        } catch (Exception e) {
            log.error("Error creating student: ", e);
            return ResponseEntity.badRequest()
                    .body(new ErrorResponse("Failed to create student: " + e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        try {
            List<Student> students = studentService.getAllStudents();
            return ResponseEntity.ok(students);
        } catch (Exception e) {
            log.error("Error fetching all students: ", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getStudent(@PathVariable Long id) {
        try {
            Student student = studentService.getStudent(id);
            if (student == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(student);
        } catch (Exception e) {
            log.error("Error fetching student with id {}: ", id, e);
            return ResponseEntity.badRequest()
                    .body(new ErrorResponse("Failed to fetch student: " + e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateStudent(
            @PathVariable Long id,
            @ModelAttribute @Valid Studentdto studentDTO) {
        try {
            log.info("Received student update request for ID: {}", id);
            Student updated = studentService.updateStudent(id, studentDTO);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            log.error("Error updating student: ", e);
            return ResponseEntity.badRequest()
                    .body(new ErrorResponse("Failed to update student: " + e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteStudent(@PathVariable Long id) {
        try {
            studentService.deleteStudent(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("Error deleting student with id {}: ", id, e);
            return ResponseEntity.badRequest()
                    .body(new ErrorResponse("Failed to delete student: " + e.getMessage()));
        }
    }
}