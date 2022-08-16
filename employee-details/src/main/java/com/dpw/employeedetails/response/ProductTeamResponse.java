package com.dpw.employeedetails.response;

import com.dpw.employeedetails.entity.ProductTeam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductTeamResponse {
    long productId;
    String name;
    public ProductTeamResponse(ProductTeam productTeam)
    {
        this.productId=productTeam.getProductId();
        this.name=productTeam.getName();
    }
}
