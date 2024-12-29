package com.StudentIS.StudentIS.dto;

import org.springframework.web.multipart.MultipartFile;

public class StudentDTO {
    private Long id;
    private String studentName;
    private String indexNo;
    private String dob;
    private String email;
    private String contactNo;
    private MultipartFile studentImage;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(String indexNo) {
        this.indexNo = indexNo;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public MultipartFile getStudentImage() {
        return studentImage;
    }

    public void setStudentImage(MultipartFile studentImage) {
        this.studentImage = studentImage;
    }
}