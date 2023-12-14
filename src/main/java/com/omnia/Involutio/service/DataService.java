package com.omnia.Involutio.service;

import com.omnia.Involutio.dto.DataDTO;
import com.omnia.Involutio.ecxeptions.NotFoundException;
import com.omnia.Involutio.entity.CSVEntity;
import com.omnia.Involutio.entity.WorkerEntity;
import com.omnia.Involutio.entity.WorkerRatingEntity;
import com.omnia.Involutio.repository.CSVRepository;
import com.omnia.Involutio.repository.WorkerRatingRepository;
import com.omnia.Involutio.repository.WorkerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor
public class DataService {

    private final WorkerMaster workerMaster;
    private final CSVRepository csvRepository;
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

        double rating = calcRating(csvEntity);
        WorkerRatingEntity workerRatingEntity = new WorkerRatingEntity(rating, worker.getId());
        workerRatingRepository.save(workerRatingEntity);
        worker.setRating((double) rating);
        workerRepository.save(worker);

    }

    private double calcRating(CSVEntity csvEntity) {
        return DataDTO.calculateEmployeeAttritionProbability(csvEntity);
//        Random random = new Random();
//        int rating = random.nextInt(71) + 10;
//        return rating;
    }

    @Bean
    private void test() {
        List<CSVEntity> entityList =  csvRepository.findAll();
        for (CSVEntity entity: entityList) {
            System.out.println(calcRating(entity));
        }
    }

}
