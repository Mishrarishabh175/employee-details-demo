package com.dpw.employeedetails.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SignUpRequest {
    @NotBlank(message = "Username required")
    @Size(min=4,max=32,message = "username should have minimum 4 and maximum 32 characters")
    private String username;
    @Email(message = "email not valid")
    private String email;
    @Size(min=8,max=32,message = "password should have minimum 8 and maximum 32 characters")
    @NotBlank(message = "password required")
    private String password;
}
