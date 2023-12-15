package com.omnia.Involutio.service;

import com.omnia.Involutio.entity.ManagerRatingEntity;
import com.omnia.Involutio.repository.ManagerRatingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ManagerRatingMaster {

    final private WorkerMaster workerMaster;

    final private ManagerRatingRepository managerRatingRepository;

    public ManagerRatingMaster(WorkerMaster workerMaster, ManagerRatingRepository managerRatingRepository) {
        this.workerMaster = workerMaster;
        this.managerRatingRepository = managerRatingRepository;
    }

    //TODO: refactor to sql after testing
    public Double getAVGwithManager(Long managerId) {
        var worker = workerMaster.getAllWithManager(managerId);
        double rating = 0;
        var size = worker.size();
        for (var i : worker) {
            size += i.getRating();
        }
        return rating / size;
    }


    public List<ManagerRatingEntity> getStatistic(LocalDate start, LocalDate end, Long managerId) {
        return managerRatingRepository.findAllByDateBetweenAndManagerId(start, end, managerId);
    }
}
