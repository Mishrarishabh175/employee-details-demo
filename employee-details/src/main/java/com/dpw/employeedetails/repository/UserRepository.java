package com.dpw.employeedetails.repository;

import com.dpw.employeedetails.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface UserRepository extends CrudRepository<User,Long> {
    User findByUserName(String userName);
}
