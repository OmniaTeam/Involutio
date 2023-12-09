package com.omnia.Involutio.repository;

import com.omnia.Involutio.entity.WorkerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkerRepository extends JpaRepository<WorkerEntity, Long> {
    List<WorkerEntity> findAllByManagerId(Long managerId);

    @Query("SELECT AVG(worker.rating) FROM WorkerEntity worker where worker.managerId = ?1")
    Double getAVG(Long managerId);
}
