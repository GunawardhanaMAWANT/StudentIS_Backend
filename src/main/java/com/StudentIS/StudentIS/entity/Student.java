package com.StudentIS.StudentIS.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String studentName;

    @Column(unique = true, nullable = false)
    private String indexNo;

    private LocalDate dob;

    @Column(unique = true)
    private String email;

    private String contactNo;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] studentImage;

    // Getters
    public Long getId() {
        return id;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getIndexNo() {
        return indexNo;
    }

    public LocalDate getDob() {
        return dob;
    }

    public String getEmail() {
        return email;
    }

    public String getContactNo() {
        return contactNo;
    }

    public byte[] getStudentImage() {
        return studentImage;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setIndexNo(String indexNo) {
        this.indexNo = indexNo;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public void setStudentImage(byte[] studentImage) {
        this.studentImage = studentImage;
    }
}