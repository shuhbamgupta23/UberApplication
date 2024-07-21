package com.shubhamgupta.project.uber.uberApp.services.impl;

import com.shubhamgupta.project.uber.uberApp.dto.DriverDto;
import com.shubhamgupta.project.uber.uberApp.dto.RideDto;
import com.shubhamgupta.project.uber.uberApp.dto.RideRequestDto;
import com.shubhamgupta.project.uber.uberApp.dto.RiderDto;
import com.shubhamgupta.project.uber.uberApp.entities.RideRequest;
import com.shubhamgupta.project.uber.uberApp.services.RiderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class RiderServiceImpl implements RiderService {

    private static final Logger log = LoggerFactory.getLogger(RiderServiceImpl.class);
    private final ModelMapper modelMapper;

    @Override
    public RideRequestDto requestRide(RideRequestDto rideRequestDto) {
        RideRequest rideRequest = modelMapper.map(rideRequestDto, RideRequest.class);
        log.info(rideRequest.toString());
        return null;
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
}
