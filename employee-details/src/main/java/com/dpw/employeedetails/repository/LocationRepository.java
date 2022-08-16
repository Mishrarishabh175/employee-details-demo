package com.dpw.employeedetails.repository;

import com.dpw.employeedetails.entity.Location;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LocationRepository extends CrudRepository<Location,Long> {
}
