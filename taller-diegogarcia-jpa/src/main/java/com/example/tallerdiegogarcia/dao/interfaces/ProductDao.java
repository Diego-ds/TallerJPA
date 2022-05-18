package com.example.tallerdiegogarcia.dao.interfaces;

import java.util.List;

import com.example.tallerdiegogarcia.model.Product;


public interface ProductDao {
	public void save(Product prod);
	public void update(Product prod);
	public void delete(Product prod);
	public List<Product> findAll();
	public Product findById(Integer id);
	public List<Product> findBySubcategoryid(Integer id);
	public List<Product> findByModel(Integer id);
	public List<Product> findBySizeunitmeasure(Integer id); 
	public List<Product> findByWorkorderQuantity();
}
