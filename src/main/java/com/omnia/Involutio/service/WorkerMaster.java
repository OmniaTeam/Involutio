package com.omnia.Involutio.service;

import com.omnia.Involutio.entity.WorkerEntity;
import com.omnia.Involutio.repository.WorkerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class WorkerMaster { //TODO:покрыть исключением
    final private WorkerRepository workerRepository;
    final private RatingMaster ratingMaster;


    public WorkerMaster(WorkerRepository workerRepository, RatingMaster ratingMaster) {
        this.workerRepository = workerRepository;
        this.ratingMaster = ratingMaster;
    }

    public List<WorkerEntity> getAllwithManager(Long managerId){
        try {
            return workerRepository.getAllByManagerId(managerId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateRating(Long workerId){
        var worker = workerRepository.findById(workerId);
        try {
        worker.ifPresent(workerEntity -> workerEntity.setRating(ratingMaster.getAVRwithWorker(workerId)));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }




}
