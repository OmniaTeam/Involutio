package com.omnia.Involutio.repository;

import com.omnia.Involutio.entity.CSVEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface CSVRepository extends JpaRepository<CSVEntity, Long> {

    Boolean existsByEmailIgnoreCaseAndDate(String email, LocalDate date);
}
