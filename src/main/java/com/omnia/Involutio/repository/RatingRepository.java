package com.omnia.Involutio.repository;

import com.omnia.Involutio.entity.RatingEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends CrudRepository<RatingEntity,Long> {

    List<RatingEntity> getAllByWorkerId(Long workerId);
}
