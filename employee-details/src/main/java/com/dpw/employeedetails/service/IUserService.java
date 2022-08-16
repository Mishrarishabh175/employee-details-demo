package com.dpw.employeedetails.service;

import com.dpw.employeedetails.request.SignInRequest;
import com.dpw.employeedetails.request.SignUpRequest;
import com.dpw.employeedetails.response.ApiResponse;
import org.springframework.security.core.Authentication;

public interface IUserService {
    ApiResponse addUser(SignUpRequest signUpRequest);
//    Authentication authenticateUser(SignInRequest signInRequest);
}
