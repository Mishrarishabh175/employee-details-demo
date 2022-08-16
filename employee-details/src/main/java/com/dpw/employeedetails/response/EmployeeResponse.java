package com.dpw.employeedetails.response;

import com.dpw.employeedetails.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocationResponse location;
    private RoleResponse role;
    private ProductTeamResponse product;
    private AddressResponse address;
    private boolean active;


    public static EmployeeResponse createEmployeeResponse(Employee employee){
        return new EmployeeResponse(
                employee.getEmpId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                new LocationResponse(employee.getLocation()),
                new RoleResponse(employee.getRole()),
                new ProductTeamResponse(employee.getProductTeam()),
                new AddressResponse(employee.getAddress()),
                employee.isActive());
    }
}
