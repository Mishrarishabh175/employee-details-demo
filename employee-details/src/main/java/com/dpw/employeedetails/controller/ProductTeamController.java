package com.dpw.employeedetails.controller;

import com.dpw.employeedetails.request.ProductTeamRequest;
import com.dpw.employeedetails.response.ProductTeamResponse;
import com.dpw.employeedetails.service.IProductTeamService;
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
public class ProductTeamController {
    @Autowired
    IProductTeamService productTeamService;

    @GetMapping("/products")
    public ResponseEntity<List<ProductTeamResponse>> getAllProducts(){
        return new ResponseEntity<>(productTeamService.getAllProducts(), HttpStatus.OK);
    }
    @PostMapping("/products")
    public ResponseEntity<ProductTeamResponse> saveProduct(@RequestBody @Valid ProductTeamRequest productTeamRequest){
        return new ResponseEntity<>(productTeamService.saveProduct(productTeamRequest),HttpStatus.CREATED);
    }
}
