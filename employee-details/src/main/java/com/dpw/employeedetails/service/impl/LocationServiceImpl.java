package com.dpw.employeedetails.service.impl;

import com.dpw.employeedetails.entity.Location;
import com.dpw.employeedetails.repository.LocationRepository;
import com.dpw.employeedetails.request.LocationRequest;
import com.dpw.employeedetails.response.LocationResponse;
import com.dpw.employeedetails.service.ILocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class LocationServiceImpl implements ILocationService {
    @Autowired
    LocationRepository locationRepository;
    @Override
    public List<LocationResponse> getAllLocations() {
        List<Location> locations = (List<Location>)locationRepository.findAll();
        List<LocationResponse> locationResponse = locations.stream().map(location->new LocationResponse(location)).collect(Collectors.toList());
        return locationResponse;
    }

    @Override
    public LocationResponse saveLocation(LocationRequest request) {
        Location location = new Location();
        location.setDescription(request.getDescription());
        location.setName(request.getName());
        return new LocationResponse(locationRepository.save(location));
    }
}
