package com.omnia.Involutio.service;

import com.omnia.Involutio.entity.ManagerEntity;
import com.omnia.Involutio.repository.ManagerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ManagerMaster {
    final private ManagerRepository managerRepository;

    public ManagerMaster(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    public List<ManagerEntity> getAll(){
        return managerRepository.findAll();
    }
}
