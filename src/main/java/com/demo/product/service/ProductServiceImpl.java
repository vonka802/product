package com.demo.product.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.product.dao.ProductDAO;
import com.demo.product.model.Product;
import com.demo.product.model.ProductDTO;

import jakarta.transaction.Transactional;

@Service
public class ProductServiceImpl implements ProductService {

	private ModelMapper modelMapper;
	
	
	private ProductDAO theProductDAO;
	@Autowired
	public ProductServiceImpl(ProductDAO theProductDAO, ModelMapper modelMapper) {
		this.theProductDAO = theProductDAO;
		this.modelMapper = modelMapper;
	}
	
	public <D, E> D convertToType(E entity, Class<D> toClass) {
		return modelMapper.map(entity, toClass);
	}

	public <D, E> List<D> convertToTypeList(List<E> entityList, Class<D> toClass) {
		return entityList.stream().map(entity -> convertToType(entity, toClass)).collect(Collectors.toList());
	}
	@Override
	public List<ProductDTO> getProducts() {
		// TODO Auto-generated method stub
		List<Product> theProducts = theProductDAO.getProduct();
		
		return convertToTypeList(theProducts,ProductDTO.class);
	}

	@Override
	public ProductDTO getProductById(int id) {
		// TODO Auto-generated method stub
		return convertToType(theProductDAO.getProductById(id),ProductDTO.class);
	}
	@Transactional
	@Override
	public ProductDTO createProduct(ProductDTO theProductDTO) {
		// TODO Auto-generated method stub
		Product theProduct = convertToType(theProductDTO,Product.class);
		return convertToType(theProductDAO.createProduct(theProduct),ProductDTO.class);
	}
	@Transactional
	@Override
	public void removeProduct(int id) {
		// TODO Auto-generated method stub
		theProductDAO.removeProduct(id);
	}
	
}
