package com.shubhamgupta.project.uber.uberApp.services.impl;

import com.shubhamgupta.project.uber.uberApp.dto.DriverDto;
import com.shubhamgupta.project.uber.uberApp.dto.SignupDto;
import com.shubhamgupta.project.uber.uberApp.dto.UserDto;
import com.shubhamgupta.project.uber.uberApp.entities.User;
import com.shubhamgupta.project.uber.uberApp.entities.enums.Role;
import com.shubhamgupta.project.uber.uberApp.exceptions.RunTimeConflictException;
import com.shubhamgupta.project.uber.uberApp.repositories.UserRepository;
import com.shubhamgupta.project.uber.uberApp.services.AuthService;
import com.shubhamgupta.project.uber.uberApp.services.RiderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RiderService riderService;

    @Override
    public String login(String email, String password) {
        return "";
    }

    @Override
    public UserDto signup(SignupDto signupDto) {
      User existingUser =  userRepository.findByEmail(signupDto.getEmail()).orElse(null);
      if(existingUser != null){
        throw new  RunTimeConflictException("Cannot signup, User already already exists with email: "+ signupDto.getEmail());
      }
        User user = modelMapper.map(signupDto, User.class);
        user.setRoles(Set.of(Role.RIDER));
        User savedUser = userRepository.save(user);
        riderService.createNewRider(savedUser);
        return modelMapper.map(savedUser, UserDto.class);

    }

    @Override
    public DriverDto onboardNewDriver(Long userId) {
        return null;
    }
}
