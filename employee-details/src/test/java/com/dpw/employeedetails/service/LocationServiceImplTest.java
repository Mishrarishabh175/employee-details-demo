package com.dpw.employeedetails.service;

import com.dpw.employeedetails.entity.Location;
import com.dpw.employeedetails.entity.TestEntityGenerator;
import com.dpw.employeedetails.repository.LocationRepository;
import com.dpw.employeedetails.request.LocationRequest;
import com.dpw.employeedetails.request.TestRequestGenerator;
import com.dpw.employeedetails.response.LocationResponse;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class LocationServiceImplTest {
    @MockBean
    private LocationRepository locationRepository;

    @Autowired
    private ILocationService locationService;

    @Test
    public void getAllLocationsReturnsLocationsList(){
        Iterable<Location> locationList = TestEntityGenerator.randomLocationList();

        Mockito.when(locationRepository.findAll()).thenReturn(locationList);

        assertThat(locationService.getAllLocations()).asList();
        assertThat(locationService.getAllLocations()).hasSize(((List<Location>)locationList).size());
    }

    @Test
    public void saveLocationReturnsSameLocationResponse(){
        LocationRequest locationRequest = TestRequestGenerator.randomLocationRequest();
        Location location = new Location();
        Faker faker = new Faker();
        location.setLocationId(faker.number().randomNumber());
        location.setName(locationRequest.getName());
        location.setDescription(locationRequest.getDescription());
        Mockito.when(locationRepository.save(any(Location.class))).thenReturn(location);

        assertThat(locationService.saveLocation(locationRequest)).isInstanceOf(LocationResponse.class);
        assertThat(locationService.saveLocation(locationRequest)).returns(locationRequest.getName(),(loc)->loc.getName());
    }
}
