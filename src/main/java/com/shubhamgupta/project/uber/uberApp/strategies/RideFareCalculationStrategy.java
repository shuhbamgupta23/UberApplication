package com.shubhamgupta.project.uber.uberApp.strategies;


import com.shubhamgupta.project.uber.uberApp.entities.RideRequest;

public interface RideFareCalculationStrategy {

    double RIDE_FARE_MULTIPLIER = 10;
    double calculateFare(RideRequest rideRequest);

}
