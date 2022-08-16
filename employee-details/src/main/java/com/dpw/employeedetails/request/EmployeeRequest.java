package com.dpw.employeedetails.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@Builder
public class EmployeeRequest {
    private long id;
    @NotBlank(message = "firstName required")
    @Size(min=2,max=32,message = "Number of characters in first name should be between 2 and 32")
    private String firstName;
    private String lastName;
    @Email(message = "email address not valid")
    private String email;
    private long roleId;
    private long productId;
    private long locationId;
    private boolean active;
    @Valid
    private AddressRequest address;
}
