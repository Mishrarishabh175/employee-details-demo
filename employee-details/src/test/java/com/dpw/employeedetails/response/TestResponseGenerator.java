package com.dpw.employeedetails.response;

import com.dpw.employeedetails.entity.TestEntityGenerator;
import com.github.javafaker.Faker;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TestResponseGenerator {
    private static final Faker faker = new Faker();

    public static EmployeeListResponse randomEmployeeListResponse(){
        List<EmployeeResponse> employeeResponse = Collections
                .singletonList(TestEntityGenerator.randomEmployee())
                .stream()
                .map(EmployeeResponse::createEmployeeResponse)
                .collect(Collectors.toList());
        return new EmployeeListResponse(employeeResponse,
                1,1,1,1,false,false
                );
    }
}
