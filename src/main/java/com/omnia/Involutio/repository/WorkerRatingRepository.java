package com.omnia.Involutio.repository;

import com.omnia.Involutio.entity.WorkerRatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WorkerRatingRepository extends JpaRepository<WorkerRatingEntity,Long> {

    List<WorkerRatingEntity> findAllByWorkerId(Long workerId);

    List<WorkerRatingEntity> findAllByDateBetween(LocalDate startDate, LocalDate endDate);
}
