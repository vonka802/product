package com.demo.product.dao;

import java.util.List;

import com.demo.product.model.Product;
import com.demo.product.model.ProductDTO;

public interface ProductDAO {

	public List<Product> getProduct() ;
	public Product getProductById(int id);
	
	public Product createProduct(Product theProduct);
	
	public void removeProduct( int id);
	
}
