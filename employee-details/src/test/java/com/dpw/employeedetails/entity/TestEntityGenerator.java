package com.dpw.employeedetails.entity;

import com.github.javafaker.Faker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Function;

public class TestEntityGenerator {
    private static final Faker faker = new Faker();

    public static Location randomLocation(){
        Location location = new Location();
        location.setLocationId(faker.number().randomNumber());
        location.setName(faker.address().cityName());
        location.setDescription(faker.address().secondaryAddress());

        return location;
    }
    public static Iterable<Location> randomLocationList(){
        int size=faker.number().numberBetween(0,5);
        List<Location> locationList= new ArrayList<>(size);
        for(int i=0;i<size;i++)
        {
            locationList.add(randomLocation());
        }
        return locationList;
    }

    public static ProductTeam randomProduct(){
        ProductTeam productTeam = new ProductTeam();
        productTeam.setProductId(faker.number().randomNumber());
        productTeam.setName(faker.programmingLanguage().name());
        productTeam.setDescription(faker.programmingLanguage().creator());
        return  productTeam;
    }

    public static Role randomRole(){
        Role role = new Role();
        role.setRoleId(faker.number().randomNumber());
        role.setName(faker.company().profession());
        role.setDescription(faker.company().catchPhrase());
        return role;
    }
    public static Employee randomEmployee(){
        return new Employee(faker.number().randomNumber(),
                faker.name().firstName(),
                faker.name().lastName(),
                faker.name().firstName()+"."+faker.name().lastName()+"@dpworld.com",
                faker.random().nextBoolean(),
                randomRole(),
                randomAddress(),
                randomLocation(),
                randomProduct());
    }
    public static Address randomAddress(){
        return new Address(faker.number().randomNumber(),
                "",
                faker.address().buildingNumber(),
                faker.address().streetAddress(),
                faker.address().cityName(),
                faker.address().state(),
                faker.number().numberBetween(100000,999999),
                null);
    }
    public static User randomUser(){
        return new User(faker.number().randomNumber(),
                faker.name().username(),
                faker.name().firstName(),
                faker.name().firstName()+"@dpworld.com");
    }
    public static org.springframework.security.core.userdetails.User randomUserDetails(){
        List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("admin"));
        User user = randomUser();
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), authorities);
    }

    public static Page randomPage(){

        return new Page() {
            Employee employee = randomEmployee();
            @Override
            public int getTotalPages() {
                return 1;
            }

            @Override
            public long getTotalElements() {
                return 1;
            }

            @Override
            public Page map(Function converter) {
                return null;
            }

            @Override
            public int getNumber() {
                return 1;
            }

            @Override
            public int getSize() {
                return 1;
            }

            @Override
            public int getNumberOfElements() {
                return 1;
            }

            @Override
            public List getContent() {
                return Collections.singletonList(employee);
            }

            @Override
            public boolean hasContent() {
                return true;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public boolean isFirst() {
                return true;
            }

            @Override
            public boolean isLast() {
                return true;
            }

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }

            @Override
            public Pageable nextPageable() {
                return null;
            }

            @Override
            public Pageable previousPageable() {
                return null;
            }

            @Override
            public Iterator iterator() {
                return null;
            }
        };
    }
}
