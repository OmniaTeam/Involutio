package com.omnia.Involutio.repository;

import com.omnia.Involutio.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<FileEntity,Long> {
}
