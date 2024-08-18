package com.shubhamgupta.project.uber.uberApp.services.impl;

import com.shubhamgupta.project.uber.uberApp.dto.DriverDto;
import com.shubhamgupta.project.uber.uberApp.dto.SignupDto;
import com.shubhamgupta.project.uber.uberApp.dto.UserDto;
import com.shubhamgupta.project.uber.uberApp.entities.Driver;
import com.shubhamgupta.project.uber.uberApp.entities.User;
import com.shubhamgupta.project.uber.uberApp.entities.enums.Role;
import com.shubhamgupta.project.uber.uberApp.exceptions.ResourceNotFoundException;
import com.shubhamgupta.project.uber.uberApp.exceptions.RunTimeConflictException;
import com.shubhamgupta.project.uber.uberApp.repositories.UserRepository;
import com.shubhamgupta.project.uber.uberApp.services.AuthService;
import com.shubhamgupta.project.uber.uberApp.services.DriverService;
import com.shubhamgupta.project.uber.uberApp.services.RiderService;
import com.shubhamgupta.project.uber.uberApp.services.WalletService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RiderService riderService;
    private final WalletService walletService;
    private final DriverService driverService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String[] login(String email, String password) {
        String tokens[] = new String[2];

        return tokens;
    }

    @Override
    @Transactional
    public UserDto signup(SignupDto signupDto) {
        User user = userRepository.findByEmail(signupDto.getEmail()).orElse(null);
        if (user != null)
            throw new RunTimeConflictException("Cannot signup, User already exists with email " + signupDto.getEmail());

        User mappedUser = modelMapper.map(signupDto, User.class);
        mappedUser.setRoles(Set.of(Role.RIDER));
        mappedUser.setPassword(passwordEncoder.encode(mappedUser.getPassword()));
        User savedUser = userRepository.save(mappedUser);

        riderService.createNewRider(savedUser);
        walletService.createNewWallet(savedUser);

        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public DriverDto onboardNewDriver(Long userId, String vehicleId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with userId: " + userId));
        if (user.getRoles().contains(Role.DRIVER)) {
            throw new RunTimeConflictException("User with id: " + userId + " is already a driver.");
        }

        user.getRoles().add(Role.DRIVER);
        User savedUser = userRepository.save(user);

        Driver driver = Driver.builder()
                .user(savedUser)
                .rating(0.0)
                .vehicleId(vehicleId)
                .available(true)
                .build();

        driverService.createNewDriver(driver);

        return modelMapper.map(driver, DriverDto.class);


    }
}
