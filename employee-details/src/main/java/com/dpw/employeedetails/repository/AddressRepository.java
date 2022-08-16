package com.dpw.employeedetails.repository;

import com.dpw.employeedetails.entity.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address,Long> {
}
