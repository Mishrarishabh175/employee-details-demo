package com.dpw.employeedetails.service;

import com.dpw.employeedetails.entity.Role;
import com.dpw.employeedetails.entity.TestEntityGenerator;
import com.dpw.employeedetails.repository.RoleRepository;
import com.dpw.employeedetails.request.RoleRequest;
import com.dpw.employeedetails.request.TestRequestGenerator;
import com.dpw.employeedetails.response.RoleResponse;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RoleServiceImplTest {
    @MockBean
    RoleRepository roleRepository;

    @Autowired
    IRoleService roleService;

    @Test
    public void  getAllRolesReturnsRoleList(){
        Role role = TestEntityGenerator.randomRole();
        List<Role> roleList = Arrays.asList(role);
        Iterable<Role> roleIterable = (Iterable<Role> )roleList;
        Mockito.when(roleRepository.findAll()).thenReturn(roleIterable);

        assertThat(roleService.getAllRoles()).asList().hasSize(1);
    }

    @Test
    public void saveProductReturnsSameProductResponse(){
        RoleRequest roleRequest = TestRequestGenerator.randomRoleRequest();
        Role role = new Role();
        Faker faker = new Faker();
        role.setRoleId(faker.number().randomNumber());
        role.setName(roleRequest.getName());
        role.setDescription(roleRequest.getDescription());
        when(roleRepository.save(any(Role.class))).thenReturn(role);

        assertThat(roleService.saveRole(roleRequest)).isInstanceOf(RoleResponse.class)
                .returns(roleRequest.getName(),(r)->r.getName());
    }
}
