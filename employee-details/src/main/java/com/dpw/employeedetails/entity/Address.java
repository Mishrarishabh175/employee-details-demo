package com.dpw.employeedetails.entity;

import com.dpw.employeedetails.request.AddressRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long addressId;

    private String lane1;

    private String lane2;

    private String street;

    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private long pinCode;

    @OneToOne(mappedBy = "address")
    private Employee employee;

    public Address(AddressRequest addressRequest)
    {
        this.lane1=addressRequest.getLane1();
        this.lane2=addressRequest.getLane2();
        this.street=addressRequest.getStreet();
        this.state=addressRequest.getState();
        this.city=addressRequest.getCity();
        this.pinCode=addressRequest.getPinCode();
    }
}
