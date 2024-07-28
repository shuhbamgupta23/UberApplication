package com.shubhamgupta.project.uber.uberApp.strategies;


import org.springframework.stereotype.Component;

@Component
public class RideStrategyManager {

    public DriverMatchingStrategy driverMatchingStrategy(double riderRating) {
        return null;
    }


    public RideFareCalculationStrategy rideFareCalculationStrategy() {
        return null;
    }

}
