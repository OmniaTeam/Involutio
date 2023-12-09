package com.omnia.Involutio.service;

import com.omnia.Involutio.repository.RatingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RatingMaster {//TODO:покрыть исключением
final private RatingRepository ratingRepository;
    public RatingMaster(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public Double getAVRwithWorker(Long workerId) {
        var rating_list = ratingRepository.getAllByWorkerId(workerId);
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

}
