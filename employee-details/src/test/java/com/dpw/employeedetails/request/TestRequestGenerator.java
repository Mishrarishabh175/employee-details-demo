package com.dpw.employeedetails.request;

import com.dpw.employeedetails.entity.Role;
import com.dpw.employeedetails.entity.TestEntityGenerator;
import com.github.javafaker.Faker;

public class TestRequestGenerator {
    private static final Faker faker = new Faker();

    public static LocationRequest randomLocationRequest(){
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setName(faker.address().cityName());
        locationRequest.setDescription(faker.address().secondaryAddress());
        return locationRequest;
    }

    public static ProductTeamRequest randomProductRequest(){
        ProductTeamRequest productTeamRequest = new ProductTeamRequest();
        productTeamRequest.setName(faker.programmingLanguage().name());
        productTeamRequest.setDescription(faker.programmingLanguage().creator());
        return productTeamRequest;
    }

    public static RoleRequest randomRoleRequest(){
        RoleRequest roleRequest =new RoleRequest();
        roleRequest.setName(faker.company().profession());
        roleRequest.setName(faker.company().catchPhrase());
        return roleRequest;
    }
    public static AddressRequest randomAddressRequest(){
        return AddressRequest
                .builder()
                .city(faker.address().cityName())
                .state(faker.address().state())
                .pinCode(faker.number().numberBetween(100000,999999))
                .build();
    }
    public static EmployeeRequest randomEmployeeRequest(){
        return EmployeeRequest
                .builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.name().firstName()+"@dpworld.com")
                .roleId(faker.number().randomNumber())
                .locationId(faker.number().randomNumber())
                .productId(faker.number().randomNumber())
                .active(faker.random().nextBoolean())
                .address(randomAddressRequest())
                .build();
    }
}
