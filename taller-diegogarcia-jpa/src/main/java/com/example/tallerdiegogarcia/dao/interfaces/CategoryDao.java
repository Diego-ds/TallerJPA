package com.example.tallerdiegogarcia.dao.interfaces;

import java.util.List;

import com.example.tallerdiegogarcia.model.Productcategory;

public interface CategoryDao {
	public void save(Productcategory category);
	public void update(Productcategory category);
	public void delete(Productcategory category);
	public List<Productcategory> findAll();
	public Productcategory findById(Integer id);
}
