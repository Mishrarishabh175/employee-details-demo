package com.dpw.employeedetails.service;

import com.dpw.employeedetails.request.LocationRequest;
import com.dpw.employeedetails.response.LocationResponse;

import java.util.List;

public interface ILocationService {
    List<LocationResponse> getAllLocations();
    LocationResponse saveLocation(LocationRequest request);


}
