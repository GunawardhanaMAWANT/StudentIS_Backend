package com.StudentIS.StudentIS.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
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
}
