package com.omnia.Involutio.repository;

import com.omnia.Involutio.entity.RatingEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends CrudRepository<RatingEntity,Long> {
}
