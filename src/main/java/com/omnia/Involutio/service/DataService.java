package com.omnia.Involutio.service;

import com.omnia.Involutio.ecxeptions.NotFoundException;
import com.omnia.Involutio.entity.CSVEntity;
import com.omnia.Involutio.entity.WorkerEntity;
import com.omnia.Involutio.entity.WorkerRatingEntity;
import com.omnia.Involutio.repository.WorkerRatingRepository;
import com.omnia.Involutio.repository.WorkerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor
public class DataService {

    private final WorkerMaster workerMaster;
    private final WorkerRepository workerRepository;
    private final WorkerRatingRepository workerRatingRepository;

    public void dataCsvProcessing(CSVEntity csvEntity) {
        WorkerEntity worker;
        try {
            String mail = csvEntity.getEmail();
            worker = workerMaster.getWorkerByEmail(mail);
        }
        catch (NotFoundException ex) {
            log.info(ex.getMessage());
            return;
        }

        int rating = calcRating(csvEntity);
        WorkerRatingEntity workerRatingEntity = new WorkerRatingEntity(rating, worker.getId());
        workerRatingRepository.save(workerRatingEntity);
        worker.setRating((double) rating);
        workerRepository.save(worker);

    }

    private int calcRating(CSVEntity csvEntity) {
        Random random = new Random();
        int rating = random.nextInt(71) + 10;
        return rating;
    }

}
