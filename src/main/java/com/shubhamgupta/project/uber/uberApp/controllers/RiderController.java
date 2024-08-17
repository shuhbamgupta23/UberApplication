package com.shubhamgupta.project.uber.uberApp.controllers;

import com.shubhamgupta.project.uber.uberApp.dto.DriverDto;
import com.shubhamgupta.project.uber.uberApp.dto.RideDto;
import com.shubhamgupta.project.uber.uberApp.dto.RideRequestDto;
import com.shubhamgupta.project.uber.uberApp.dto.RiderDto;
import com.shubhamgupta.project.uber.uberApp.services.RiderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rider")
@RequiredArgsConstructor
public class RiderController {

    private final RiderService riderService;

    @PostMapping("/requestRide")
    public ResponseEntity<RideRequestDto> requestRide(@RequestBody RideRequestDto rideRequestDto) {
        return ResponseEntity.ok(riderService.requestRide(rideRequestDto));
    }

    @PostMapping("/cancelRide/{rideId}")
    public ResponseEntity<RideDto> cancelRide(@PathVariable Long rideId) {
        return ResponseEntity.ok(riderService.cancelRide(rideId));
    }

    @PostMapping("/rateRider/{driverId}/{rating}")
    public ResponseEntity<DriverDto> rateDriver(@PathVariable Long driverId, Integer rating) {
        return ResponseEntity.ok(riderService.rateDriver(driverId, rating));
    }

    @GetMapping("/getMyProfile")
    public ResponseEntity<RiderDto> getMyProfile() {
        return ResponseEntity.ok(riderService.getMyProfile());
    }

    @GetMapping("/getMyRides")
    public ResponseEntity<Page<RideDto>> getMyRides(@RequestParam(defaultValue = "0") Integer pageOffSet,
                                                    @RequestParam(defaultValue = "10", required = false) Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageSize, pageSize, Sort.by(Sort.Direction.DESC, "createdTime", "id"));
        return ResponseEntity.ok(riderService.getAllMyRides(pageRequest));
    }


}
