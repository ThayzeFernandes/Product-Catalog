package com.productCatalog.models.dto;

import java.math.BigDecimal;

import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.NotBlank;


public class ProductDTO {
	
	@NotBlank
    private String name;
    
	@PositiveOrZero
    private BigDecimal price;
	
	@NotBlank
    private String description;
    

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
