package com.dpw.employeedetails.response;

import com.dpw.employeedetails.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleResponse {
    long roleId;
    String name;
    public RoleResponse(Role role)
    {
        this.roleId=role.getRoleId();
        this.name=role.getName();
    }
}
