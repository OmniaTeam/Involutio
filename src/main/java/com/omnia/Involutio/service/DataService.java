package com.omnia.Involutio.service;

import com.omnia.Involutio.dto.DataDTO;
import com.omnia.Involutio.dto.RegressionCoordinates;
import com.omnia.Involutio.ecxeptions.NotFoundException;
import com.omnia.Involutio.entity.CSVEntity;
import com.omnia.Involutio.entity.WorkerEntity;
import com.omnia.Involutio.entity.WorkerRatingEntity;
import com.omnia.Involutio.repository.WorkerRatingRepository;
import com.omnia.Involutio.repository.WorkerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DataService {

    private final WorkerMaster workerMaster;
    private final WorkerRepository workerRepository;
    private final WorkerRatingMaster workerRatingMaster;
    private final WorkerRatingRepository workerRatingRepository;
    private final LinearMaster linearMaster;

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
        worker.setRating(rating);
        List<Integer> last7Days = workerRatingMaster.getLast7Days(worker.getId()).stream().map(WorkerRatingEntity::getRating).toList();
        RegressionCoordinates coordinates = linearMaster.getCoordinates(last7Days);
        worker.setRegression_k(coordinates.k());
        worker.setRegression_b(coordinates.b());
        workerRepository.save(worker);

    }

    private int calcRating(CSVEntity csvEntity) {
        return DataDTO.calculateEmployeeAttritionProbability(csvEntity);
    }

}
