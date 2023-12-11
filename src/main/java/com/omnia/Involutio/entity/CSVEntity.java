package com.omnia.Involutio.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Entity
@Data
@Slf4j
@NoArgsConstructor
public class CSVEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
