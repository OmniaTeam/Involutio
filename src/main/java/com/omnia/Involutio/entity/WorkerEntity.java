package com.omnia.Involutio.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;


@Entity
@Log
@NoArgsConstructor
@Data
public class WorkerEntity {
    @Id
    private Long id;
    private String FIO;
    private String mail;
    private Long managerId;
}
