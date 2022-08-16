package com.dpw.employeedetails.service.impl;

import com.dpw.employeedetails.entity.ProductTeam;
import com.dpw.employeedetails.repository.ProductTeamRepository;
import com.dpw.employeedetails.request.ProductTeamRequest;
import com.dpw.employeedetails.response.ProductTeamResponse;
import com.dpw.employeedetails.service.IProductTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductTeamServiceImpl implements IProductTeamService {
    @Autowired
    ProductTeamRepository productTeamRepository;
    @Override
    public List<ProductTeamResponse> getAllProducts() {
        List<ProductTeam> productTeams = (List<ProductTeam>) productTeamRepository.findAll();
        List<ProductTeamResponse> productTeamResponse = productTeams.stream().map(productTeam -> new ProductTeamResponse(productTeam)).collect(Collectors.toList());
        return productTeamResponse;
    }

    @Override
    public ProductTeamResponse saveProduct(ProductTeamRequest request) {
        ProductTeam productTeam = new ProductTeam();
        productTeam.setName(request.getName());
        productTeam.setDescription(request.getDescription());
        return new ProductTeamResponse(productTeamRepository.save(productTeam));
    }
}
