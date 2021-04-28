package com.productCatalog.controllers;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.productCatalog.models.Product;
import com.productCatalog.models.dto.ExceptionResponse;
import com.productCatalog.models.dto.ProductDTO;
import com.productCatalog.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/products")
@Tag(name = "Product Controller", description = "Product Management")
public class ProductController {

	@Autowired
	private ProductService productService;

	
	@PostMapping
	@Operation(summary = "Insert one Products", responses = {
			@ApiResponse(responseCode = "201", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))),
			@ApiResponse(responseCode = "400", description = "Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))) })
	public ResponseEntity<Product> createProduct(@Valid @RequestBody ProductDTO productDTO) {
		Product product = new Product();
		product.setName(productDTO.getName());
		product.setPrice(productDTO.getPrice());
		product.setDescription(productDTO.getDescription());
		
		return new ResponseEntity<Product>(this.productService.createProduct(product), HttpStatus.CREATED);
	}
	
	@PutMapping("{id}")
	@Operation(summary = "Change a Product by id ", responses = {
			@ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))),
			@ApiResponse(responseCode = "404", description = "Error", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "400", description = "Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))) })
	public ResponseEntity<Product> changeProduct(@PathVariable String id, @Valid @RequestBody ProductDTO productDTO)
			throws NotFoundException {
		return new ResponseEntity<Product>(productService.changeProduct(id, productDTO), HttpStatus.OK);
	}
	
	@GetMapping
	@Operation(summary = "Find All Products", responses = {
			@ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))) })
	public ResponseEntity<List<Product>> getProducts() {
		List<Product> products = productService.getAllProducts();
		return ResponseEntity.ok().body(products);
	}

	@GetMapping("{id}")
	@Operation(summary = "Find a Product by id ", responses = {
			@ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))),
			@ApiResponse(responseCode = "404", description = "Error", content = @Content(mediaType = "application/json")) })
	public ResponseEntity<Product> getProductById(@PathVariable String id) throws NotFoundException {
		return new ResponseEntity<Product>(productService.getProductById(id), HttpStatus.OK);
	}

	@GetMapping("/search")
	@Operation(summary = "Find a Product by filter ", responses = {
			@ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))) })
	public ResponseEntity<List<Product>> getProductByFilter(@RequestParam(name = "q", required = false) String q,
			@RequestParam(name = "min_price", required = false) BigDecimal min_price,
			@RequestParam(name = "max_price", required = false) BigDecimal max_price) {
		return new ResponseEntity<List<Product>>(productService.getProductByFilter(q, min_price, max_price),
				HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	@Operation(summary = "Delete a Product by id ", responses = {
			@ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))),
			@ApiResponse(responseCode = "404", description = "Error", content = @Content(mediaType = "application/json")) })
	public ResponseEntity<Product> deleteProductById(@PathVariable String id) throws NotFoundException {
		productService.deleteProduct(id);
		return new ResponseEntity<Product>(HttpStatus.OK);
	}

}
