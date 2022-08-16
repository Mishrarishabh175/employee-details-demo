package com.dpw.employeedetails.service.impl;

import com.dpw.employeedetails.entity.Role;
import com.dpw.employeedetails.repository.RoleRepository;
import com.dpw.employeedetails.request.RoleRequest;
import com.dpw.employeedetails.response.RoleResponse;
import com.dpw.employeedetails.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    RoleRepository roleRepository;
    @Override
    public List<RoleResponse> getAllRoles() {
        List<Role> roles = (List<Role>) roleRepository.findAll();
        List<RoleResponse> roleResponse = roles.stream().map(role -> new RoleResponse(role)).collect(Collectors.toList());
        return roleResponse;
    }

    @Override
    public RoleResponse saveRole(RoleRequest request) {
        Role role = new Role();
        role.setName(request.getName());
        role.setDescription(request.getDescription());
        return new RoleResponse(roleRepository.save(role));
    }
}
