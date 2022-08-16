package com.dpw.employeedetails.service;

import com.dpw.employeedetails.request.ProductTeamRequest;
import com.dpw.employeedetails.response.ProductTeamResponse;

import java.util.List;

public interface IProductTeamService {
    List<ProductTeamResponse> getAllProducts();
    ProductTeamResponse saveProduct(ProductTeamRequest request);
}
