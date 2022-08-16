package com.dpw.employeedetails.service;

import com.dpw.employeedetails.request.RoleRequest;
import com.dpw.employeedetails.response.RoleResponse;

import java.util.List;

public interface IRoleService {
    List<RoleResponse> getAllRoles();
    RoleResponse saveRole(RoleRequest request);
}

