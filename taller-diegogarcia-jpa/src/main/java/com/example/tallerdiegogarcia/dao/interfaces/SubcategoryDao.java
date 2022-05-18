package com.example.tallerdiegogarcia.dao.interfaces;

import java.time.LocalDate;
import java.util.List;

import com.example.tallerdiegogarcia.model.Productsubcategory;

public interface SubcategoryDao {
	public void save(Productsubcategory prod);
	public void update(Productsubcategory prod);
	public void delete(Productsubcategory prod);
	public List<Productsubcategory> findAll();
	public Productsubcategory findById(Integer id);
	public List<Productsubcategory> findByName(String name);
	public List<Productsubcategory> findByCategory(Integer id);
	public List<Object[]> findbyDateAndCategories(Integer categoryId,LocalDate sellstartdate,
            LocalDate sellenddate);
}
