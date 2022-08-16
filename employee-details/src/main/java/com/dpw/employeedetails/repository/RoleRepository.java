package com.dpw.employeedetails.repository;

import com.dpw.employeedetails.entity.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleRepository extends CrudRepository<Role,Long> {
}
