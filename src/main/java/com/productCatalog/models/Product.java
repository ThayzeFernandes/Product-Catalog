package com.productCatalog.models;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.annotations.GenericGenerator;

@Entity(name = "product")
public class Product {
	
	@Id
    @GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy ="uuid")
    private String id;
	
	@NotBlank
    private String name;
	
	@PositiveOrZero
    private BigDecimal price;
    
	@NotBlank
    private String description;
    

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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
