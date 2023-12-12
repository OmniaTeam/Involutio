package com.omnia.Involutio.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class WorkerRatingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double rating;
    private Long workerId;
    private LocalDate date;


    public WorkerRatingEntity(double rating, Long workerId, Long date) {
        this.rating = rating;
        this.workerId = workerId;
        this.date = LocalDate.now();
    }
}
