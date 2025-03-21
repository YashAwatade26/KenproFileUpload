package com.kenpro.pojos;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.*;
@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class FileDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String documentName;

    @Column(nullable = false)
    private String documentType;

    private String description;
    private String receivedFrom;
    private LocalDateTime receivedDate;
    private LocalDateTime uploadDate;

    @Lob
    @Column(columnDefinition = "VARBINARY(MAX)", nullable = false)
    private byte[] fileData;
    
    @Column(nullable = false)
    private boolean deleted = false;

}

