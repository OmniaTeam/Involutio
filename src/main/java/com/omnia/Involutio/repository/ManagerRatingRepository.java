package com.omnia.Involutio.repository;

import com.omnia.Involutio.entity.ManagerRatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ManagerRatingRepository extends JpaRepository<ManagerRatingEntity, Long> {

    Boolean existsByDateAndManagerId(LocalDate date, Long managerId);

    List<ManagerRatingEntity> findAllByDateBetweenAndManagerId(LocalDate start, LocalDate end, Long managerId);
}
