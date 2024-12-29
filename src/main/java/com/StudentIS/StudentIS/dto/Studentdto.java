package com.StudentIS.StudentIS.dto;

// StudentDTO.java

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class Studentdto {
    private Long id;
    private String studentName;
    private String indexNo;
    private String dob;
    private String email;
    private String contactNo;
    private MultipartFile studentImage;
}