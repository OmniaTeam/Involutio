package com.omnia.Involutio.service;

import com.omnia.Involutio.repository.ManagerRatingRepository;
import org.springframework.stereotype.Service;

@Service
public class ManagerRatingMaster {

    final private WorkerMaster workerMaster;

    final private ManagerRatingRepository managerRatingRepository;

    public ManagerRatingMaster(WorkerMaster workerMaster, ManagerRatingRepository managerRatingRepository) {
        this.workerMaster = workerMaster;
        this.managerRatingRepository = managerRatingRepository;
    }

    public Double getAVGwithManager(Long managerId){


    }



}
