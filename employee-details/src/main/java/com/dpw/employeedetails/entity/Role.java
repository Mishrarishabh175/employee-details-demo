package com.dpw.employeedetails.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long roleId;

    @Column(nullable = false)
    private String name;

    private String description;

    @OneToMany(mappedBy = "role")
    private List<Employee> employees;
}
