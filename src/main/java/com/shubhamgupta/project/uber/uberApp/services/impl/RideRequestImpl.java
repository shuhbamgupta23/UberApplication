package com.shubhamgupta.project.uber.uberApp.services.impl;

import com.shubhamgupta.project.uber.uberApp.entities.RideRequest;
import com.shubhamgupta.project.uber.uberApp.exceptions.ResourceNotFoundException;
import com.shubhamgupta.project.uber.uberApp.repositories.RideRequestRepository;
import com.shubhamgupta.project.uber.uberApp.services.RideRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class RideRequestImpl implements RideRequestService {

    private final RideRequestRepository rideRequestRepository;


    @Override
    public RideRequest findRideRequestById(Long rideRequestId) {
        return rideRequestRepository.findById(rideRequestId).orElseThrow(() -> new ResourceNotFoundException("Ride not found with requestId " + rideRequestId));
    }

    @Override
    public void update(RideRequest rideRequest) {
        RideRequest toSave = rideRequestRepository.findById(rideRequest.getId()).orElseThrow(() -> new ResourceNotFoundException("RideRequest does not exist with id: "+rideRequest.getId()));
        rideRequestRepository.save(rideRequest);
    }
}
