package com.dpw.employeedetails.response;

import com.dpw.employeedetails.entity.Location;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LocationResponse {
    long locationId;
    String name;

    public LocationResponse(Location location)
    {
        this.locationId=location.getLocationId();
        this.name=location.getName();
    }
}
