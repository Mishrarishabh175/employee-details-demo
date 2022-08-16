package com.dpw.employeedetails.Controller;

import com.dpw.employeedetails.Security.JwtTokenUtil;
import com.dpw.employeedetails.controller.EmployeeController;
import com.dpw.employeedetails.entity.TestEntityGenerator;
import com.dpw.employeedetails.request.SignInRequest;
import com.dpw.employeedetails.response.ApiResponse;
import com.dpw.employeedetails.response.EmployeeListResponse;
import com.dpw.employeedetails.response.SignInResponse;
import com.dpw.employeedetails.response.TestResponseGenerator;
import com.dpw.employeedetails.service.IEmployeeService;
import com.jayway.jsonpath.JsonPath;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.net.ssl.SSLEngineResult;
import javax.validation.ConstraintViolationException;

import java.net.URI;

import static com.dpw.employeedetails.Controller.BaseControllerTest.authorizationHeader;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerTest {
    @MockBean
    private IEmployeeService employeeService;
    @MockBean
    private JwtTokenUtil jwtTokenUtil;
    @MockBean
    private UserDetailsService userDetailsService;
    @Autowired
    TestRestTemplate restTemplate;


    @BeforeEach
    public void signIn(){
        User user = TestEntityGenerator.randomUserDetails();

        Mockito.when(jwtTokenUtil.getUsernameFromToken(anyString()))
                .thenReturn(user.getUsername());
        Mockito.when(userDetailsService.loadUserByUsername(user.getUsername()))
                .thenReturn(user);
        Mockito.when(jwtTokenUtil.validateToken("token",user))
                .thenReturn(true);
    }
    @Test
    public void givenEmployees_whenGetEmployees_returnJsonEmployeeListResponse() throws Exception
    {

        EmployeeListResponse employeeListResponse = TestResponseGenerator.randomEmployeeListResponse();

        Mockito.when(employeeService.getAllEmployees(any())).thenReturn(employeeListResponse);

        URI targetUrl = UriComponentsBuilder.fromUriString("/employees")
                        .queryParam("page",1)
                        .queryParam("entries",5)
                        .build().encode().toUri();
        HttpEntity<String> entity = new HttpEntity<>(authorizationHeader());
        ResponseEntity<EmployeeListResponse> response = restTemplate.exchange(targetUrl,
                HttpMethod.GET,entity,EmployeeListResponse.class);

        assertThat(response.getBody().getEntries()).isEqualTo(1);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(employeeListResponse.getEmployees().size()).isEqualTo(response.getBody().getEmployees().size());
    }
    @Test
    public void givenEmployees_whenGetEmployeesWithInvalidParams_returnBadRequest() throws Exception
    {

        EmployeeListResponse employeeListResponse = TestResponseGenerator.randomEmployeeListResponse();

        Mockito.when(employeeService.getAllEmployees(any())).thenReturn(employeeListResponse);

        URI targetUrl = UriComponentsBuilder.fromUriString("/employees")
                .queryParam("page",0)
                .queryParam("entries",250)
                .build().encode().toUri();
        HttpEntity<String> entity = new HttpEntity<>(authorizationHeader());
        ResponseEntity<ApiResponse> response = restTemplate.exchange(targetUrl,
                HttpMethod.GET,entity,ApiResponse.class);
        System.out.println(response);
        assertThat(response.getBody().isSuccess()).isFalse();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
   @Test
    public  void givenFirstName_whenSearchEmployee_ReturnSearchedValue(){
        EmployeeListResponse employeeListResponse = TestResponseGenerator.randomEmployeeListResponse();
        Mockito.when(employeeService.searchByDifferentValues(anyString(),anyString(),anyString(),any(),any(),any(),any())).thenReturn(employeeListResponse);

        URI targetUrl = UriComponentsBuilder.fromUriString("/employees/search")
                .queryParam("firstName",employeeListResponse.getEmployees().get(0).getFirstName())
                .build().encode().toUri();
       HttpEntity<String> entity = new HttpEntity<>(authorizationHeader());
       ResponseEntity<EmployeeListResponse> response = restTemplate.exchange(targetUrl,
               HttpMethod.GET,entity,EmployeeListResponse.class);

       assertThat(response.getBody().getEntries()).isEqualTo(1);
       assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
       assertThat(employeeListResponse.getEmployees().size()).isEqualTo(response.getBody().getEmployees().size());

    }

}
