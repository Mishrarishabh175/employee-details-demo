package com.dpw.employeedetails.service.impl;

import com.dpw.employeedetails.entity.*;
import com.dpw.employeedetails.exception.NotFoundException;
import com.dpw.employeedetails.repository.*;
import com.dpw.employeedetails.request.EmployeeRequest;
import com.dpw.employeedetails.response.ApiResponse;
import com.dpw.employeedetails.response.EmployeeListResponse;
import com.dpw.employeedetails.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    AddressRepository addressRepository;
    @Override
    public EmployeeListResponse getAllEmployees(Pageable pageRequest) {
        Page<Employee> employeePage= employeeRepository.findAll(pageRequest);
        return EmployeeListResponse.createEmployeeList(employeePage);
    }

    @Override
    public ApiResponse SaveEmployee(EmployeeRequest request) {
        Employee employee = new Employee();
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setEmail(request.getEmail());

        Role role= new Role();
        role.setRoleId(request.getRoleId());
        employee.setRole(role);

        Location location = new Location();
        location.setLocationId(request.getLocationId());
        employee.setLocation(location);

        ProductTeam productTeam = new ProductTeam();
        productTeam.setProductId(request.getProductId());
        employee.setProductTeam(productTeam);

        Address address = new Address(request.getAddress());
        employee.setAddress(address);
        addressRepository.save(address);
        employee.setAddress(address);

        employee.setActive(request.isActive());
        employeeRepository.save(employee);
        return new ApiResponse(true,"Employee Successfully Added");
    }

    @Override
    public ApiResponse deleteEmployeeById(long id) {
        try{
            employeeRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e)
        {
            throw new NotFoundException("Employee Not found");
        }
        return new ApiResponse(true,"Employee deleted Successfully");
    }


    @Override
    public EmployeeListResponse searchByDifferentValues(String firstName,String lastName,String email,Long roleId,Long locationId,Long productId,Pageable pageRequest) {
        Page<Employee> employeesPage;
        Role role =new Role();
        Location location = new Location();
        ProductTeam productTeam = new ProductTeam();

        if(roleId==null && locationId==null && productId==null ){
            employeesPage=employeeRepository
                    .findByFirstNameStartingWithAndLastNameStartingWithAndEmailStartingWithAllIgnoreCase(
                            firstName,
                            lastName,
                            email,
                            pageRequest);
        }
        else if(roleId==null && locationId == null)
        {
            productTeam.setProductId(productId);
            employeesPage=employeeRepository
                    .findByFirstNameStartingWithAndLastNameStartingWithAndEmailStartingWithAndProductTeamAllIgnoreCase(
                            firstName,
                            lastName,
                            email,
                            productTeam,
                            pageRequest);
        }
        else if(roleId == null && productId==null)
        {
            location.setLocationId(locationId);
            employeesPage=employeeRepository
                    .findByFirstNameStartingWithAndLastNameStartingWithAndEmailStartingWithAndLocationAllIgnoreCase(
                            firstName,
                            lastName,
                            email,
                            location,
                            pageRequest);
        }
        else if(locationId==null && productId==null)
        {
            role.setRoleId(roleId);
            employeesPage=employeeRepository
                    .findByFirstNameStartingWithAndLastNameStartingWithAndEmailStartingWithAndRoleAllIgnoreCase(
                            firstName,
                            lastName,
                            email,
                            role,
                            pageRequest);
        }
        else if(roleId==null)
        {
            location.setLocationId(locationId);
            productTeam.setProductId(productId);
            employeesPage=employeeRepository
                    .findByFirstNameStartingWithAndLastNameStartingWithAndEmailStartingWithAndLocationAndProductTeamAllIgnoreCase(
                            firstName,
                            lastName,
                            email,
                            location,
                            productTeam,
                            pageRequest);
        }
        else if(locationId==null)
        {
            role.setRoleId(roleId);
            productTeam.setProductId(productId);
            employeesPage=employeeRepository
                    .findByFirstNameStartingWithAndLastNameStartingWithAndEmailStartingWithAndRoleAndProductTeamAllIgnoreCase(
                            firstName,
                            lastName,
                            email,
                            role,
                            productTeam,
                            pageRequest);
        }
        else if (productId == null)
        {
            role.setRoleId(roleId);
            location.setLocationId(locationId);
            employeesPage=employeeRepository
                    .findByFirstNameStartingWithAndLastNameStartingWithAndEmailStartingWithAndRoleAndLocationAllIgnoreCase(
                            firstName,
                            lastName,
                            email,
                            role,
                            location,
                            pageRequest);
        }
        else{
            role.setRoleId(roleId);
            location.setLocationId(locationId);
            productTeam.setProductId(productId);
            employeesPage=employeeRepository
                    .findByFirstNameStartingWithAndLastNameStartingWithAndEmailStartingWithAndRoleAndLocationAndProductTeamAllIgnoreCase(
                            firstName,
                            lastName,
                            email,
                            role,
                            location,
                            productTeam,
                            pageRequest);
        }
        return EmployeeListResponse.createEmployeeList(employeesPage);
    }

    @Override
    public ApiResponse updateEmployee(EmployeeRequest request) {
        Employee employee = new Employee();
        employee.setEmpId(request.getId());
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setEmail(request.getEmail());

        Role role= new Role();
        role.setRoleId(request.getRoleId());
        employee.setRole(role);

        Location location = new Location();
        location.setLocationId(request.getLocationId());
        employee.setLocation(location);

        ProductTeam productTeam = new ProductTeam();
        productTeam.setProductId(request.getProductId());
        employee.setProductTeam(productTeam);

        Address address = new Address(request.getAddress());
        address.setAddressId(request.getAddress().getAddressId());
        employee.setAddress(address);


        employee.setActive(request.isActive());
        employeeRepository.updateEmployee(employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.isActive(),
                employee.getRole().getRoleId(),
                employee.getProductTeam().getProductId(),
                employee.getLocation().getLocationId(),
                employee.getAddress().getAddressId(),
                employee.getEmpId());
        return new ApiResponse(true,"employee updated");
    }
}
