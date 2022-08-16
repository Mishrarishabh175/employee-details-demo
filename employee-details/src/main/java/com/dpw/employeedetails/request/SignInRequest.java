package com.dpw.employeedetails.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignInRequest {
    @NotBlank(message = "Username required")
    @Size(min=4,max=16,message = "username Should have minimum 4 and maximum 16 characters")
    String username;
    @NotBlank(message = "password required")
    @Size(min=8,max=16,message = "password Should have minimum 8 and maximum 16 characters")
    String password;
}
