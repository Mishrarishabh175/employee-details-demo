package com.dpw.employeedetails.service;

import com.dpw.employeedetails.entity.Employee;
import com.dpw.employeedetails.entity.Location;
import com.dpw.employeedetails.entity.ProductTeam;
import com.dpw.employeedetails.entity.TestEntityGenerator;
import com.dpw.employeedetails.repository.AddressRepository;
import com.dpw.employeedetails.repository.EmployeeRepository;
import com.dpw.employeedetails.request.EmployeeRequest;
import com.dpw.employeedetails.request.ProductTeamRequest;
import com.dpw.employeedetails.request.TestRequestGenerator;
import com.dpw.employeedetails.response.ApiResponse;
import com.dpw.employeedetails.response.EmployeeListResponse;
import com.dpw.employeedetails.response.ProductTeamResponse;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EmployeeServiceImplTest {
    @MockBean
    EmployeeRepository employeeRepository;
    @MockBean
    AddressRepository addressRepository;
    @Autowired
    IEmployeeService employeeService;

    @Test
    public void givenEmployeeList_whenGetAllEmployees_thenReturnEmployeeListResponse(){
        Page<Employee> page = TestEntityGenerator.randomPage();

        Mockito.when(employeeRepository.findAll((Pageable) any())).thenReturn(page);
        EmployeeListResponse expectedResponse = EmployeeListResponse.createEmployeeList(page);
        EmployeeListResponse actualResponse = employeeService.getAllEmployees(any());
        assertThat(actualResponse.getEmployees().get(0).getFirstName()).isEqualTo(expectedResponse.getEmployees().get(0).getFirstName());
    }

    @Test
    public void givenEmployeeRequest_whenSaveEmployees_thenReturnSuccessfulApiResponse(){
        EmployeeRequest employeeRequest = TestRequestGenerator.randomEmployeeRequest();

        when(employeeRepository.save(any())).thenReturn(null);

        when(addressRepository.save(any())).thenReturn(null);

        ApiResponse apiResponse =employeeService.SaveEmployee(employeeRequest);
        assertThat(apiResponse.isSuccess()).isTrue();
        assertThat(apiResponse.getMessage()).isEqualTo("Employee Successfully Added");
    }
    @Test
    public void givenEmployeeId_whenDeleteEmployees_thenThrowNotFoundException(){
        doThrow(EmptyResultDataAccessException.class).when(employeeRepository.deleteById(anyLong()));
    }
}
