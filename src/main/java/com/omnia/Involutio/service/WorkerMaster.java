package com.omnia.Involutio.service;

import com.omnia.Involutio.ecxeptions.GlobalHandlerException;
import com.omnia.Involutio.entity.WorkerEntity;
import com.omnia.Involutio.repository.WorkerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class WorkerMaster { //TODO:покрыть исключением
    final private WorkerRepository workerRepository;


    public WorkerMaster(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    public List<WorkerEntity> getAllwithManager(Long managerId){
        return workerRepository.getAllByManagerId(managerId);
    }


}
