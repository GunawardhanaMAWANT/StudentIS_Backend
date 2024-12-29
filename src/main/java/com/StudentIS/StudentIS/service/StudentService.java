package com.StudentIS.StudentIS.service;

import com.StudentIS.StudentIS.entity.Student;
import com.StudentIS.StudentIS.repo.StudentRepository;
import com.StudentIS.StudentIS.dto.StudentDTO;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.time.format.DateTimeFormatter;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Create
    public Student createStudent(StudentDTO studentDTO) {
        Student student = new Student();
        return getStudent(studentDTO, student);
    }

    // Read all
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Read one
    public Student getStudent(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + id));
    }

    // Update
    public Student updateStudent(Long id, StudentDTO studentDTO) {
        Student student = getStudent(id);
        return getStudent(studentDTO, student);
    }

    private Student getStudent(StudentDTO studentDTO, Student student) {
        student.setStudentName(studentDTO.getStudentName());
        student.setIndexNo(studentDTO.getIndexNo());
        student.setEmail(studentDTO.getEmail());
        student.setContactNo(studentDTO.getContactNo());
        if (studentDTO.getDob() != null) {
            student.setDob(LocalDate.parse(studentDTO.getDob(), dateFormatter));
        }
        return studentRepository.save(student);
    }

    // Delete
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new EntityNotFoundException("Student not found with id: " + id);
        }
        studentRepository.deleteById(id);
    }
}
