package com.omnia.Involutio.service;

import com.omnia.Involutio.ecxeptions.NotFoundException;
import com.omnia.Involutio.entity.ManagerEntity;
import com.omnia.Involutio.entity.ManagerRatingEntity;
import com.omnia.Involutio.repository.ManagerRatingRepository;
import com.omnia.Involutio.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ManagerRatingMaster {

    final private WorkerMaster workerMaster;
    final private ManagerRatingRepository managerRatingRepository;
    final private ManagerMaster managerMaster;
    final private ManagerRepository managerRepository;



    //TODO: refactor to sql after testing
    public int getAVGwithManager(Long managerId){
        var worker = workerMaster.getAllWithManager(managerId);
        int rating = 0;
        var size = worker.size();
        for (var i : worker){
            rating += i.getRating();
        }
        return rating/size;
    }

    public void updateManagerRating(Long managerId){
        try {
            ManagerEntity managerEntity = managerMaster.getWithManagerId(managerId);
            int updateRating = getAVGwithManager(managerId);
            managerEntity.setRating(updateRating);
            managerRepository.save(managerEntity);
            ManagerRatingEntity managerRatingEntity = new ManagerRatingEntity(updateRating, managerId);
            managerRatingRepository.save(managerRatingEntity);
        }
        catch (NotFoundException ex) {
            log.error(ex.getMessage());
        }
    }


    public List<ManagerRatingEntity> getStatistic(LocalDate start, LocalDate end, Long managerId) {
        return managerRatingRepository.findAllByDateBetweenAndManagerId(start, end, managerId);
    }
}
