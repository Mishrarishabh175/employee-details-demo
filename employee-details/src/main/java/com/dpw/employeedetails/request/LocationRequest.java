package com.dpw.employeedetails.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class LocationRequest {
    private long locationId;
    @NotBlank(message = "Name of location required")
    private String name;
    @NotBlank(message = "Description required")
    private String Description;
}
