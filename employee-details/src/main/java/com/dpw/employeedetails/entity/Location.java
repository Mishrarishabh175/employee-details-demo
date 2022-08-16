package com.dpw.employeedetails.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long locationId;

    @Column(nullable = false)
    private String name;

    private String description;

    @OneToMany(mappedBy = "location")
    private List<Employee> employees;
}
