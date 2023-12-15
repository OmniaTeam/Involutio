package com.omnia.Involutio.service;

import com.omnia.Involutio.ecxeptions.NotFoundException;
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

    public ManagerEntity getWithUser(Long userId){
        return managerRepository.findByUserId(userId).orElseThrow(() -> new NotFoundException(String.format("Manager with user id %d", userId)));
    }

    public ManagerEntity getWithManagerId(Long managerId){
        return managerRepository.findById(managerId).orElseThrow(() -> new NotFoundException(String.format("Manager with manager id %d", managerId)));
    }



}
