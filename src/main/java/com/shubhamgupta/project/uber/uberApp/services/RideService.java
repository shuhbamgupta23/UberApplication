package com.shubhamgupta.project.uber.uberApp.services;

import com.shubhamgupta.project.uber.uberApp.dto.RideRequestDto;
import com.shubhamgupta.project.uber.uberApp.entities.Driver;
import com.shubhamgupta.project.uber.uberApp.entities.Ride;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RideService {

    Ride getRideById(Long rideId);

    void matchWithDriver(RideRequestDto rideRequestDto);

    Ride createNewRide(RideRequestDto rideRequestDto, Driver driver);

    Ride updateRideStatus(Long rideId, Long rideStatus);

    Page<Ride> getAllRidesOfRider(Long riderId, PageRequest pageRequest);

    Page<Ride> getAllRidesOfDriver(Long driverId, PageRequest pageRequest);
}
