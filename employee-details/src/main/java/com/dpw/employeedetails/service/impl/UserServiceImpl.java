package com.dpw.employeedetails.service.impl;

import com.dpw.employeedetails.entity.User;
import com.dpw.employeedetails.repository.UserRepository;
import com.dpw.employeedetails.request.SignInRequest;
import com.dpw.employeedetails.request.SignUpRequest;
import com.dpw.employeedetails.response.ApiResponse;
import com.dpw.employeedetails.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
//    @Autowired
//    AuthenticationManager authenticationManager;
    @Override
    public ApiResponse addUser(SignUpRequest signUpRequest) {
        User user = new User();
        user.setUserName(signUpRequest.getUsername());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        userRepository.save(user);
        return new ApiResponse(true,"User Added SuccessFully");
    }

//    @Override
//    public Authentication authenticateUser(SignInRequest signInRequest) {
//        UsernamePasswordAuthenticationToken authenticationToken =
//                new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword());
//        Authentication authentication = authenticationManager.authenticate(authenticationToken);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        return authentication;
//    }
}
