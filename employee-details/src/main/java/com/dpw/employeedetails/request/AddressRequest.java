package com.dpw.employeedetails.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.*;

@Getter
@Setter
@ToString
@Builder
public class AddressRequest {
    private long addressId;
    private String lane1="";
    private String lane2="";
    private String street="";
    @NotBlank(message = "City required")
    private String city;
    @NotBlank(message = "State required")
    private String state;
    @NotNull
    @Min(100000)
    @Max(999999)
    private long pinCode;
}
