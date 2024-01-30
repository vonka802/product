package com.demo.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.demo.product.model.ProductDTO;
import com.demo.product.service.ProductService;


@RestController
@RequestMapping("/inventory")
public class ProductController {

	
	ProductDTO theProductDto = new ProductDTO();
	
	
	 private ProductService theProductService;
	    @Autowired
		public ProductController(ProductService theProductService) {
			this.theProductService = theProductService;
		}
	  
	    @GetMapping("/products")
		public List<ProductDTO> getProducts()
		{
	    	System.out.println("hellow");
			return theProductService.getProducts();	
		} 
	    
	    @GetMapping("/products/{id}")
	  		public ProductDTO getProductsById(@PathVariable int id)
	  		{
	  			return theProductService.getProductById(id);	
	  		}   
	    @PostMapping("/products")
  		public ProductDTO createProduct(@RequestBody ProductDTO productDto)
  		{
  			return theProductService.createProduct(productDto);
  		} 
	    @PutMapping("/products")
  		public ProductDTO updateProduct(@RequestBody ProductDTO productDto)
  		{
  			return theProductService.createProduct(productDto);
  		} 
	    @DeleteMapping("/products/{id}")
  		public void removeProduct(@PathVariable int id)
  		{
  			 theProductService.removeProduct(id);
  		} 
	    
}
