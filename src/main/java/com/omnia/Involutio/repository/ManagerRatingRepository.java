package com.omnia.Involutio.repository;

import com.omnia.Involutio.entity.ManagerRatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRatingRepository extends JpaRepository<ManagerRatingEntity, Long> {
}
