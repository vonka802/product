package com.demo.product.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.product.model.Product;
import com.demo.product.model.ProductDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class ProductDAOImpl implements ProductDAO {

	private EntityManager entityManager;

	public ProductDAOImpl() {
		super();
	}

	@Autowired
	public ProductDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	@Override
	public List<Product> getProduct() {
		// TODO Auto-generated method stub
		TypedQuery<Product> theQuery = entityManager.createQuery("FROM Product", Product.class);

		return theQuery.getResultList();
	}

	@Override
	public Product getProductById(int id) {
		// TODO Auto-generated method stub
		return entityManager.find(Product.class, id);
	}

	@Override
	public Product createProduct(Product theProduct) {
		// TODO Auto-generated method stub
		return entityManager.merge(theProduct);
	}

	@Override
	public void removeProduct(int id) {
		// TODO Auto-generated method stub
		Product theProduct = getProductById(id);
		entityManager.remove(theProduct);
	}
	
}
