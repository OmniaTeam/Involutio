package com.omnia.Involutio.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
@Data
public class WorkerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long managerId;
    private String FIO;
    private String speciality;
    @Column(unique = true)
    private String mail;
    private double regression_k;
    private double regression_b;
    private Integer rating;
}
