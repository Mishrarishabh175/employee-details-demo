package com.dpw.employeedetails.controller;

import com.dpw.employeedetails.Security.JwtTokenUtil;
import com.dpw.employeedetails.request.SignInRequest;
import com.dpw.employeedetails.request.SignUpRequest;
import com.dpw.employeedetails.response.ApiResponse;
import com.dpw.employeedetails.response.SignInResponse;
import com.dpw.employeedetails.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {
    @Autowired
    IUserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;
    @PostMapping("/signUp")
    public ResponseEntity<ApiResponse> addUser(@RequestBody @Valid SignUpRequest signUpRequest)
    {
        return new ResponseEntity<>(userService.addUser(signUpRequest), HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<SignInResponse> userSignIn(@RequestBody SignInRequest signInRequest) throws Exception {
        authenticate(signInRequest.getUsername(), signInRequest.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(signInRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new SignInResponse(token));
    }
    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
