package com.dpw.employeedetails.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RoleRequest {
    private long roleId;
    @NotBlank(message = "name of role required")
    private String name;
    @NotBlank(message = "Description required")
    private String Description;
}
