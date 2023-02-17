package com.example.kafka.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data @ToString @AllArgsConstructor @NoArgsConstructor

public class etudiant {
    private Long id;
    private String name;
    private String email;
}
