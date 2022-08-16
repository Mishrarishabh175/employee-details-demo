package com.dpw.employeedetails.repository;

import com.dpw.employeedetails.entity.Employee;
import com.dpw.employeedetails.entity.Location;
import com.dpw.employeedetails.entity.ProductTeam;
import com.dpw.employeedetails.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;
import org.springframework.data.domain.Pageable;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee,Long> {
    @Modifying
    @Transactional
    @Query(value = "Update Employee e set e.first_name= ?1,e.last_name=?2,e.email=?3,e.active=?4,e.role_id=?5,e.product_id=?6,e.location_id=?7,e.address_id=?8 where e.emp_id = ?9",nativeQuery = true)
    int updateEmployee(String firstName,String lastName,String email,boolean active,long roleId,long productId,long locationId,long AddressId,long empId);

    Page<Employee> findAll(Pageable pageRequest);
    Page<Employee> findByFirstNameStartingWithAndLastNameStartingWithAndEmailStartingWithAndRoleAndLocationAndProductTeamAllIgnoreCase(String firstName,
                                                                                                                        String lastName,
                                                                                                                        String email,
                                                                                                                        Role role,
                                                                                                                        Location location,
                                                                                                                        ProductTeam productTeam,
                                                                                                                        Pageable pageRequest);

    Page<Employee> findByFirstNameStartingWithAndLastNameStartingWithAndEmailStartingWithAndRoleAllIgnoreCase(String firstName,
                                                                                                  String lastName,
                                                                                                  String email,
                                                                                                  Role role,
                                                                                                  Pageable pageRequest);

    Page<Employee> findByFirstNameStartingWithAndLastNameStartingWithAndEmailStartingWithAndLocationAllIgnoreCase(String firstName,
                                                                                                      String lastName,
                                                                                                      String email,
                                                                                                      Location location,
                                                                                                      Pageable pageRequest);

    Page<Employee> findByFirstNameStartingWithAndLastNameStartingWithAndEmailStartingWithAndProductTeamAllIgnoreCase(String firstName,
                                                                                                         String lastName,
                                                                                                         String email,
                                                                                                         ProductTeam productTeam,
                                                                                                         Pageable pageRequest);

    Page<Employee> findByFirstNameStartingWithAndLastNameStartingWithAndEmailStartingWithAndLocationAndProductTeamAllIgnoreCase(String firstName,
                                                                                                                    String lastName,
                                                                                                                    String email,
                                                                                                                    Location location,
                                                                                                                    ProductTeam productTeam,
                                                                                                                    Pageable pageRequest);

    Page<Employee> findByFirstNameStartingWithAndLastNameStartingWithAndEmailStartingWithAndRoleAndProductTeamAllIgnoreCase(String firstName,
                                                                                                                String lastName,
                                                                                                                String email,
                                                                                                                Role role,
                                                                                                                ProductTeam productTeam,
                                                                                                                Pageable pageRequest);

    Page<Employee> findByFirstNameStartingWithAndLastNameStartingWithAndEmailStartingWithAndRoleAndLocationAllIgnoreCase(String firstName,
                                                                                                             String lastName,
                                                                                                             String email,
                                                                                                             Role role,
                                                                                                             Location location,
                                                                                                             Pageable pageRequest);

    Page<Employee> findByFirstNameStartingWithAndLastNameStartingWithAndEmailStartingWithAllIgnoreCase(String firstName,
                                                                                           String lastName,
                                                                                           String email,
                                                                                           Pageable pageRequest);
}
