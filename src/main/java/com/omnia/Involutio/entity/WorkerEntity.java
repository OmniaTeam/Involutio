package com.omnia.Involutio.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;


@Entity
@NoArgsConstructor
@Data
public class WorkerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String FIO;
    private String mail;
    private Long managerId;
    private Double rating;
}
