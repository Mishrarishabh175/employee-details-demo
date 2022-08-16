package com.dpw.employeedetails.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class ProductTeam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;

    @Column(nullable = false)
    private String name;

    private String description;

    @OneToMany(mappedBy = "productTeam")
    private List<Employee> employees;
}
