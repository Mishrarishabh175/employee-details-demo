package com.dpw.employeedetails.service;

import com.dpw.employeedetails.request.EmployeeRequest;
import com.dpw.employeedetails.response.ApiResponse;
import com.dpw.employeedetails.response.EmployeeListResponse;
import com.dpw.employeedetails.response.EmployeeResponse;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface IEmployeeService {
    EmployeeListResponse getAllEmployees(Pageable pageRequest);
    ApiResponse SaveEmployee(EmployeeRequest request);
    ApiResponse deleteEmployeeById(long Id);

    EmployeeListResponse searchByDifferentValues(String firstName,String lastName,String email,Long roleId,Long locationId,Long productId,Pageable pageRequest);
    ApiResponse updateEmployee(EmployeeRequest request);
}
