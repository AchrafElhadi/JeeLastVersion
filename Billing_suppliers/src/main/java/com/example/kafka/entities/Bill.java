package com.example.kafka.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bill {
    private Long id;
    private Date billingDate;
    private List<ProductItems> productItemsList;
    private long customerID;
    private etudiant customer;
}