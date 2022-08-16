package com.dpw.employeedetails.controller;

import com.dpw.employeedetails.request.EmployeeRequest;
import com.dpw.employeedetails.response.ApiResponse;
import com.dpw.employeedetails.response.EmployeeListResponse;
import com.dpw.employeedetails.response.EmployeeResponse;
import com.dpw.employeedetails.service.IEmployeeService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.websocket.server.PathParam;
import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController
@Slf4j
@Validated
public class EmployeeController {
    @Autowired
    IEmployeeService employeeService;
    @GetMapping("/employees")
    public ResponseEntity<EmployeeListResponse> getAllEmployees(@PathParam("page") @Min(value = 1,message = "First Page is 1") Integer page,
                                                                @PathParam("entries") @Max(value = 100,message = "Max Entries returned are 100")Integer entries){
        if(page==null) page=1;
        if(entries==null) entries=20;
        Pageable pageRequest = (Pageable) PageRequest.of(page-1, entries, Sort.by(Sort.Direction.ASC, "firstName","lastName") );
        return new ResponseEntity<>(employeeService.getAllEmployees(pageRequest), HttpStatus.OK);
    }
    @GetMapping("/employees/search")
    public ResponseEntity<EmployeeListResponse> searchEmployee(@PathParam("firstName") String firstName,
                                                                 @PathParam("lastName") String lastName,
                                                                 @PathParam("email") String email,
                                                                 @PathParam("roleId") Long roleId,
                                                                 @PathParam("locationId") Long locationId,
                                                                 @PathParam("productId") Long productId,
                                                                 @PathParam("page") @Min(value = 1,message = "First Page is 1") Integer page,
                                                                 @PathParam("entries") @Max(value = 100,message = "Max Entries returned are 100") Integer entries)
    {
        if(page==null) page=1;
        if(entries==null) entries=20;
        if(firstName == null) firstName="";
        if(lastName == null) lastName="";
        if(email == null) email = "";
        log.info("Search firstName:{} lastName:{} email:{} roleId:{} locationId:{} productId:{} page:{} entries:{}",
                firstName,lastName,email,roleId,locationId,productId,page,entries);
        Pageable pageRequest = (Pageable) PageRequest.of(page-1, entries, Sort.by(Sort.Direction.ASC, "firstName","lastName") );
        return new ResponseEntity<>(employeeService.searchByDifferentValues(firstName,lastName,email,roleId,locationId,productId,pageRequest),HttpStatus.OK);
    }
    @PostMapping("/employees")
    public ResponseEntity<ApiResponse> saveEmployee(@RequestBody @Valid EmployeeRequest employeeRequest)
    {
        log.info("Employee create request received : {}, address: {}",employeeRequest.toString(),employeeRequest.getAddress().toString());
        return new ResponseEntity<>(employeeService.SaveEmployee(employeeRequest),HttpStatus.CREATED);
    }
    @DeleteMapping("/employee/{id}")
    public ResponseEntity<ApiResponse> deleteEmployeeById(@PathVariable long id)
    {
        return new ResponseEntity<>(employeeService.deleteEmployeeById(id),HttpStatus.OK);
    }
    @PutMapping("/employees")
    public  ResponseEntity<ApiResponse> updateEmployee(@RequestBody @Valid EmployeeRequest employeeRequest)
    {
        return new ResponseEntity<>(employeeService.updateEmployee(employeeRequest),HttpStatus.CREATED);
    }

}
