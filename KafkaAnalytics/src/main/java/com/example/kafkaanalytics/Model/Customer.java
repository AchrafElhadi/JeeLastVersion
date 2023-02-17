package com.example.kafkaanalytics.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data @ToString @AllArgsConstructor
public class Customer {
    private Long id;
    private String name;
    private String email;
}
