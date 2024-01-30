package com.demo.product.service;

import java.util.List;

import com.demo.product.model.ProductDTO;


public interface ProductService {
	
	public List<ProductDTO> getProducts();

	public ProductDTO getProductById(int id);
	
	public ProductDTO createProduct(ProductDTO theProductDTO);
	
	public void removeProduct( int id);
}
