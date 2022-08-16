package com.dpw.employeedetails.response;

import com.dpw.employeedetails.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeListResponse {
    private List<EmployeeResponse> employees;
    private int totalPages;
    private long totalEntries;
    private int entries;
    private int pageNumber;
    private boolean hasNext;
    private boolean hasPrevious;
    public static EmployeeListResponse createEmployeeList(Page<Employee> employeePage)
    {
        return new EmployeeListResponse(employeePage.getContent().stream().map(EmployeeResponse::createEmployeeResponse).collect(Collectors.toList()),
                employeePage.getTotalPages(),
                employeePage.getTotalElements(),
                employeePage.getNumberOfElements(),
                employeePage.getNumber()+1,
                employeePage.hasNext(),
                employeePage.hasPrevious());
    }
}
