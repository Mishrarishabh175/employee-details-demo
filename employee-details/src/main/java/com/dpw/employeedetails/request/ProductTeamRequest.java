package com.dpw.employeedetails.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ProductTeamRequest {
    private long productId;
    @NotBlank(message = "name of product required")
    private String name;
    @NotBlank(message= "Description required")
    private String Description;
}
