package com.StudentIS.StudentIS.service;

import com.StudentIS.StudentIS.entity.Student;
import com.StudentIS.StudentIS.repo.StudentRepository;
import com.StudentIS.StudentIS.dto.Studentdto;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityNotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Service
@Slf4j
public class StudentService {

    private final StudentRepository studentRepository;
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Transactional
    public Student createStudent(Studentdto dto) throws IOException {
        Student student = new Student();
        updateStudentFromDTO(student, dto);
        return studentRepository.save(student);
    }

    @Transactional(readOnly = true)
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Student getStudent(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + id));
    }

    @Transactional
    public Student updateStudent(Long id, Studentdto dto) throws IOException {
        Student student = getStudent(id);
        updateStudentFromDTO(student, dto);
        return studentRepository.save(student);
    }

    @Transactional
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new EntityNotFoundException("Student not found with id: " + id);
        }
        studentRepository.deleteById(id);
    }

    private void updateStudentFromDTO(Student student, Studentdto dto) throws IOException {
        try {
            if (dto.getStudentName() != null && !dto.getStudentName().trim().isEmpty()) {
                student.setStudentName(dto.getStudentName().trim());
            }

            if (dto.getIndexNo() != null && !dto.getIndexNo().trim().isEmpty()) {
                student.setIndexNo(dto.getIndexNo().trim());
            }

            if (dto.getEmail() != null && !dto.getEmail().trim().isEmpty()) {
                student.setEmail(dto.getEmail().trim());
            }

            if (dto.getContactNo() != null && !dto.getContactNo().trim().isEmpty()) {
                student.setContactNo(dto.getContactNo().trim());
            }

            if (dto.getDob() != null && !dto.getDob().trim().isEmpty()) {
                try {
                    student.setDob(LocalDate.parse(dto.getDob().trim(), dateFormatter));
                } catch (DateTimeParseException e) {
                    log.error("Error parsing date: {}", dto.getDob(), e);
                    throw new IllegalArgumentException("Invalid date format. Expected format: YYYY-MM-DD");
                }
            }

            if (dto.getStudentImage() != null && !dto.getStudentImage().isEmpty()) {
                student.setStudentImage(dto.getStudentImage().getBytes());
            }

            log.debug("Successfully mapped DTO to student entity");
        } catch (Exception e) {
            log.error("Error mapping DTO to student entity: ", e);
            throw new RuntimeException("Failed to process student data: " + e.getMessage());
        }
    }
}