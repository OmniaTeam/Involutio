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
    final private ManagerRatingMaster managerRatingMaster;

    public ManagerMaster(ManagerRepository managerRepository, WorkerRepository workerRepository, ManagerRatingMaster managerRatingMaster) {
        this.managerRepository = managerRepository;
        this.workerRepository = workerRepository;
        this.managerRatingMaster = managerRatingMaster;

    }

    public List<ManagerEntity> getAll() {
        return managerRepository.findAll();
    }

    public ManagerEntity getWithUser(Long userId) {
        return managerRepository.findByUserId(userId).orElseThrow(() -> new NotFoundException(String.format("Manager with user id %d", userId)));
    }

    public ManagerEntity getWithManagerId(Long managerId) {
        return managerRepository.findById(managerId).orElseThrow(() -> new NotFoundException(String.format("Manager with manager id %d", managerId)));
    }

    public void updateRating(Long managerId) {
        try {
            var manager = managerRepository.findById(managerId);
            manager.ifPresent(managerEntity -> managerEntity.setRating(managerRatingMaster.getAVGwithManager(managerId)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
