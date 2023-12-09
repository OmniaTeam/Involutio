package com.omnia.Involutio.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

@Entity
@Log
@Data
@NoArgsConstructor
public class RatingEntity {
    @Id
    private Long id;
    private double rating;
    private Long workerId;
}
