package com.shubhamgupta.project.uber.uberApp.strategies.impl;

import com.shubhamgupta.project.uber.uberApp.entities.RideRequest;
import com.shubhamgupta.project.uber.uberApp.services.DistanceService;
import com.shubhamgupta.project.uber.uberApp.strategies.RideFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RiderFareDefaultFareCalculationStrategy implements RideFareCalculationStrategy {

    private final DistanceService distanceServices;

    @Override
    public double calculateFare(RideRequest rideRequest) {
       double distance = distanceServices.calculateDistance(rideRequest.getPickupLocation(), rideRequest.getDropOffLocation());
       return distance * RIDE_FARE_MULTIPLIER;
    }
}
