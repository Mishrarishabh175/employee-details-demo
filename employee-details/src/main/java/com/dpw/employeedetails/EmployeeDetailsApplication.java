package com.dpw.employeedetails;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.dpw.employeedetails")
@EntityScan(basePackages = "com.dpw.employeedetails")
public class EmployeeDetailsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeDetailsApplication.class, args);
	}

}
