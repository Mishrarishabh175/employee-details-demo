package com.dpw.employeedetails.Controller;

import org.springframework.http.HttpHeaders;

public class BaseControllerTest {
    private static final String token = "Bearer token";

    public static HttpHeaders authorizationHeader(){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization",token);
        return headers;
    }
}
