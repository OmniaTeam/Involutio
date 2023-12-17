package com.omnia.Involutio.service;

import com.omnia.Involutio.dto.RegressionCoordinates;
import org.springframework.stereotype.Service;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import java.util.List;

@Service
public class LinearMaster {
    public RegressionCoordinates getCoordinates(List<Integer> ratings) {
        SimpleRegression regression = new SimpleRegression();
        int index = 1;
        for (int rating: ratings) {
            regression.addData(rating, index);
            index++;
        }
        double k = regression.getSlope();
        double b = regression.getIntercept();
        return new RegressionCoordinates(k, b);
    }
}
