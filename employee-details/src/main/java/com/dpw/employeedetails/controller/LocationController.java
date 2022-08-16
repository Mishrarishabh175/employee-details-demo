package com.dpw.employeedetails.controller;

import com.dpw.employeedetails.request.LocationRequest;
import com.dpw.employeedetails.response.LocationResponse;
import com.dpw.employeedetails.service.ILocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class LocationController {
    @Autowired
    ILocationService locationService;

    @GetMapping("/locations")
    public ResponseEntity<List<LocationResponse>> getAllLocations(){
        return new ResponseEntity<>(locationService.getAllLocations(),HttpStatus.OK);
    }

    @PostMapping("/locations")
    public ResponseEntity<LocationResponse> saveLocation(@RequestBody @Valid LocationRequest locationRequest)
    {
        return new ResponseEntity<>(locationService.saveLocation(locationRequest),HttpStatus.CREATED);
    }
}
