package com.shubhamgupta.project.uber.uberApp.services;

import com.shubhamgupta.project.uber.uberApp.dto.DriverDto;
import com.shubhamgupta.project.uber.uberApp.dto.RideDto;
import com.shubhamgupta.project.uber.uberApp.dto.RideRequestDto;
import com.shubhamgupta.project.uber.uberApp.dto.RiderDto;

import java.util.List;

public interface RiderService {

    RideRequestDto requestRide(RideRequestDto rideRequestDto);

    RideDto cancelRide(Long rideId);

    DriverDto rateDriver(Long rideId, Integer rating);

    RiderDto getMyProfile();

    List<RideDto> getAllMyRides();
}
