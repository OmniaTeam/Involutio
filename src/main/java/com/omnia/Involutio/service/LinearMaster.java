package com.omnia.Involutio.service;

import com.omnia.Involutio.dto.RegressionCoordinates;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import java.util.List;

@Service
@Slf4j
public class LinearMaster {
    public RegressionCoordinates getCoordinates(List<Integer> ratings) {
        SimpleRegression regression = new SimpleRegression();
        int index = 1;
        for (int rating: ratings) {
            regression.addData(rating, index);
            index++;
        }
        Double k = regression.getSlope();
        Double b = regression.getIntercept();
        log.info(String.format("k = %f", k));
        log.info(String.format("b = %f", b));
        return new RegressionCoordinates(k, b);
    }
}
