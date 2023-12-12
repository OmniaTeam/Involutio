package com.omnia.Involutio.service;

import com.omnia.Involutio.ecxeptions.NotFoundException;
import com.omnia.Involutio.entity.WorkerEntity;
import com.omnia.Involutio.repository.WorkerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class WorkerMaster {
    final private WorkerRepository workerRepository;
    final private WorkerRatingMaster workerRatingMaster;


    public WorkerMaster(WorkerRepository workerRepository, WorkerRatingMaster workerRatingMaster) {
        this.workerRepository = workerRepository;
        this.workerRatingMaster = workerRatingMaster;
    }

    public WorkerEntity getWorker(Long workerId){
        return workerRepository.findById(workerId).orElseThrow(() -> new NotFoundException(String.format("worker with user id %d", workerId)));
    }

    public List<WorkerEntity> getAllWithManager(Long managerId){
        return workerRepository.findAllByManagerId(managerId);
    }

    public void updateRating(Long workerId){
        WorkerEntity worker = getWorker(workerId);
        // TODO: сохранит ли в бд без явного save?
        worker.setRating(workerRatingMaster.getAVRwithWorker(workerId));

    }




}
