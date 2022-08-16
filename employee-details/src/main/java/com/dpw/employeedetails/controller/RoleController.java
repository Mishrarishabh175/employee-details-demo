package com.dpw.employeedetails.controller;

import com.dpw.employeedetails.request.RoleRequest;
import com.dpw.employeedetails.response.RoleResponse;
import com.dpw.employeedetails.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class RoleController {
    @Autowired
    IRoleService roleService;

    @GetMapping("/roles")
    public ResponseEntity<List<RoleResponse>> getAllRoles(){
        return new ResponseEntity<>(roleService.getAllRoles(), HttpStatus.OK);
    }
    @PostMapping("/roles")
    public ResponseEntity<RoleResponse> saveRoles(@RequestBody @Valid RoleRequest roleRequest){
        return new ResponseEntity<>(roleService.saveRole(roleRequest),HttpStatus.CREATED);
    }
}
