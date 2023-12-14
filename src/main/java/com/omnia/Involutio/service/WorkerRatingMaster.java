package com.omnia.Involutio.service;

import com.omnia.Involutio.entity.WorkerRatingEntity;
import com.omnia.Involutio.repository.ManagerRatingRepository;
import com.omnia.Involutio.repository.WorkerRatingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class WorkerRatingMaster {

final private WorkerRatingRepository workerRatingRepository;


    public WorkerRatingMaster(WorkerRatingRepository workerRatingRepository, ManagerRatingRepository managerRatingRepository) {
        this.workerRatingRepository = workerRatingRepository;
    }
    //TODO: refactor to sql after testing
    public Double getAVRwithWorker(Long workerId) {
        var rating_list = workerRatingRepository.findAllByWorkerId(workerId);
        var size = rating_list.size();
        if (size > 0) {
            double summ = (double) 0;
            for (var i : rating_list){
                summ += i.getRating();
            }
            return summ / size;
        }else {
            return (double) 0;
        }
    }

    public List<WorkerRatingEntity> getStatistic(LocalDate start, LocalDate end, Long workerId){
        return workerRatingRepository.findAllByDateBetweenAndWorkerId(start, end, workerId);
    }

    public List<WorkerRatingEntity> getLast7Days(Long workerId){
        LocalDate start = LocalDate.now().minusDays(7);
        LocalDate end = LocalDate.now();
        return workerRatingRepository.findAllByDateBetweenAndWorkerId(start, end, workerId);
    }

}
