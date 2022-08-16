package com.dpw.employeedetails.service;

import com.dpw.employeedetails.entity.ProductTeam;
import com.dpw.employeedetails.entity.TestEntityGenerator;
import com.dpw.employeedetails.repository.ProductTeamRepository;
import com.dpw.employeedetails.request.ProductTeamRequest;
import com.dpw.employeedetails.request.TestRequestGenerator;
import com.dpw.employeedetails.response.ProductTeamResponse;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductTeamServiceImplTest {

    @MockBean
    ProductTeamRepository productTeamRepository;

    @Autowired
    IProductTeamService productTeamService;

    @Test
    public void  getAllProductsReturnsProductList(){
        ProductTeam productTeam = TestEntityGenerator.randomProduct();
        List<ProductTeam> productTeamList = Collections.singletonList(productTeam);
        Iterable<ProductTeam> productTeamIterable = (Iterable<ProductTeam> )productTeamList;
        when(productTeamRepository.findAll()).thenReturn(productTeamIterable);

        assertThat(productTeamService.getAllProducts()).asList().hasSize(1);
    }

    @Test
    public void saveProductReturnsSameProductResponse(){
        ProductTeamRequest productTeamRequest = TestRequestGenerator.randomProductRequest();
        ProductTeam productTeam =new ProductTeam();
        Faker faker = new Faker();
        productTeam.setProductId(faker.number().randomNumber());
        productTeam.setName(productTeamRequest.getName());
        productTeam.setDescription(productTeamRequest.getDescription());
        when(productTeamRepository.save(any(ProductTeam.class))).thenReturn(productTeam);

        assertThat(productTeamService.saveProduct(productTeamRequest))
                .isInstanceOf(ProductTeamResponse.class)
                .returns(productTeamRequest.getName(),(prod)->prod.getName());
    }
}
