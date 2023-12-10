package com.omnia.Involutio.repository;

import com.omnia.Involutio.entity.ManagerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagerRepository extends JpaRepository<ManagerEntity, Long> {

    Optional<ManagerEntity> findByUserId(Long userId);
}
