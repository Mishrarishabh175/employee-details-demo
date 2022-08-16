package com.dpw.employeedetails.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long empId;

    @Column(nullable = false)
    private String firstName;

    private  String lastName;

    @Column(nullable = false,unique = true)
    private String email;

    private boolean active;

    @ManyToOne
    @JoinColumn(name = "role_id",referencedColumnName ="roleId")
    private Role role;

    @OneToOne
    @JoinColumn(name="address_id",referencedColumnName = "addressId")
    private Address address;

    @ManyToOne
    @JoinColumn(name="location_id",referencedColumnName = "locationId")
    private Location location;

    @ManyToOne
    @JoinColumn(name="product_id",referencedColumnName ="productId" )
    private ProductTeam productTeam;
}
