package com.shubhamgupta.project.uber.uberApp.services.impl;

import com.shubhamgupta.project.uber.uberApp.dto.DriverDto;
import com.shubhamgupta.project.uber.uberApp.dto.RideDto;
import com.shubhamgupta.project.uber.uberApp.dto.RideRequestDto;
import com.shubhamgupta.project.uber.uberApp.dto.RiderDto;
import com.shubhamgupta.project.uber.uberApp.entities.Driver;
import com.shubhamgupta.project.uber.uberApp.entities.RideRequest;
import com.shubhamgupta.project.uber.uberApp.entities.Rider;
import com.shubhamgupta.project.uber.uberApp.entities.User;
import com.shubhamgupta.project.uber.uberApp.entities.enums.RideRequestStatus;
import com.shubhamgupta.project.uber.uberApp.exceptions.ResourceNotFoundException;
import com.shubhamgupta.project.uber.uberApp.repositories.RideRequestRepository;
import com.shubhamgupta.project.uber.uberApp.repositories.RiderRepository;
import com.shubhamgupta.project.uber.uberApp.services.RiderService;
import com.shubhamgupta.project.uber.uberApp.strategies.RideStrategyManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RiderServiceImpl implements RiderService {

    private final ModelMapper modelMapper;
    private final RideStrategyManager rideStrategyManager;
    private final RideRequestRepository rideRequestRepository;
    private final RiderRepository riderRepository;


    @Override
    @Transactional
    public RideRequestDto requestRide(RideRequestDto rideRequestDto) {
        Rider rider = getCurrentRider();
        RideRequest rideRequest = modelMapper.map(rideRequestDto, RideRequest.class);
        rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);
        rideRequest.setRider(rider);
        double fare = rideStrategyManager.rideFareCalculationStrategy().calculateFare(rideRequest);
        rideRequest.setFare(fare);
        RideRequest savedRideRequest = rideRequestRepository.save(rideRequest);
        List<Driver> drivers = rideStrategyManager.driverMatchingStrategy(rider.getRating()).findMatchingDriver(rideRequest);

        //TODO : Send notification to all the driver for this request

        return modelMapper.map(savedRideRequest, RideRequestDto.class);
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        return null;
    }

    @Override
    public DriverDto rateDriver(Long rideId, Integer rating) {
        return null;
    }

    @Override
    public RiderDto getMyProfile() {
        return null;
    }

    @Override
    public List<RideDto> getAllMyRides() {
        return List.of();
    }

    @Override
    public Rider createNewRider(User user) {
        Rider rider = Rider.builder()
                .user(user)
                .rating(0.0)
                .build();

        return riderRepository.save(rider);

    }

    @Override
    public Rider getCurrentRider() {
        return riderRepository.findById(1L).orElseThrow(() -> new ResourceNotFoundException("Rider not found with id " + 1));

    }
}
