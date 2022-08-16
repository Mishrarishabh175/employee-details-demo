package com.dpw.employeedetails.response;

import com.dpw.employeedetails.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse {
    private long addressId;
    private String lane1;
    private String lane2;
    private String street;
    private String city;
    private String state;
    private long pinCode;

    public AddressResponse(Address address)
    {
        this.addressId=address.getAddressId();
        this.lane1=address.getLane1();
        this.lane2=address.getLane2();
        this.street=address.getStreet();
        this.city=address.getCity();
        this.state=address.getState();
        this.pinCode=address.getPinCode();
    }
}
