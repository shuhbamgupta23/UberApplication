package com.shubhamgupta.project.uber.uberApp.strategies;


import com.shubhamgupta.project.uber.uberApp.dto.RideRequestDto;

public interface RideFareCalculationStrategy {

    double calculateFare(RideRequestDto rideRequestDto);

}
