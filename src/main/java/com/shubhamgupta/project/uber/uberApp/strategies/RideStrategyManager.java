package com.shubhamgupta.project.uber.uberApp.strategies;


import com.shubhamgupta.project.uber.uberApp.strategies.impl.DriverMatchingHighestRatedDriverStrategy;
import com.shubhamgupta.project.uber.uberApp.strategies.impl.DriverMatchingNearestDriverStrategy;
import com.shubhamgupta.project.uber.uberApp.strategies.impl.RideFareSurgePricingFareCalculationStrategy;
import com.shubhamgupta.project.uber.uberApp.strategies.impl.RiderFareDefaultFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class RideStrategyManager {

    final private DriverMatchingHighestRatedDriverStrategy highestRatedDriverStrategy;
    final private DriverMatchingNearestDriverStrategy nearestDriverStrategy;
    final private RiderFareDefaultFareCalculationStrategy defaultFareCalculationStrategy;
    final private RideFareSurgePricingFareCalculationStrategy surgePricingFareCalculationStrategy;


    public DriverMatchingStrategy driverMatchingStrategy(double riderRating) {
        if (riderRating >= 4.8) {
            return highestRatedDriverStrategy;
        }
        return nearestDriverStrategy;
    }


    public RideFareCalculationStrategy rideFareCalculationStrategy() {

        LocalTime surgeStartTime = LocalTime.of(18, 0);
        LocalTime surgeEndTime = LocalTime.of(21, 0);
        LocalTime currentTime = LocalTime.now();
        boolean isSurgeTime = currentTime.isAfter(surgeStartTime) && currentTime.isBefore(surgeEndTime);

        if (isSurgeTime) {
            return surgePricingFareCalculationStrategy;
        }

        return defaultFareCalculationStrategy;


    }

}
