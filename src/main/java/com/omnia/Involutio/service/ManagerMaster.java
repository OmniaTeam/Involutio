package com.omnia.Involutio.service;

import com.omnia.Involutio.ecxeptions.NotFoundException;
import com.omnia.Involutio.entity.ManagerEntity;
import com.omnia.Involutio.repository.ManagerRepository;
import com.omnia.Involutio.repository.WorkerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ManagerMaster {
    final private ManagerRepository managerRepository;
    final private WorkerRepository workerRepository;

    public ManagerMaster(ManagerRepository managerRepository, WorkerRepository workerRepository) {
        this.managerRepository = managerRepository;
        this.workerRepository = workerRepository;
    }

    public List<ManagerEntity> getAll(){
        return managerRepository.findAll();
    }

    public ManagerEntity getWithUser(Long userId){
        return managerRepository.findByUserId(userId).orElseThrow(() -> new NotFoundException(String.format("Manager with user id %d", userId)));
    }

    public void updateRating(Long managerId){
        try {
            var manager = managerRepository.findById(managerId);
            manager.ifPresent(managerEntity -> managerEntity.setRating(workerRepository.getAVG(managerId)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
