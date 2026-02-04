 package com.appsdeveloperblog.ws.products.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appsdeveloperblog.ws.products.service.IProductService;



@RestController
@RequestMapping("/products") // http://localhost:<port>/products
public class ProductController {

    IProductService productService;
     

	public ProductController(IProductService productService) {
		this.productService = productService;
	}


	@PostMapping
	   public ResponseEntity<String> createProduct(@RequestBody CreateProductRestModel product) {
	       
           String productID = productService.createProduct(product);

           return ResponseEntity.status(HttpStatus.CREATED).body(productID);
	   }
	   
}
