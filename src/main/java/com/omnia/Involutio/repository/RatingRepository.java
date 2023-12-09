package com.omnia.Involutio.repository;

import com.omnia.Involutio.entity.RatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<RatingEntity,Long> {

    List<RatingEntity> findAllByWorkerId(Long workerId);

    List<RatingEntity> findAllByDateBetween(LocalDate startDate, LocalDate endDate);
}
