package com.example.tallerdiegogarcia.dao.imp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.tallerdiegogarcia.dao.interfaces.CategoryDao;
import com.example.tallerdiegogarcia.model.Productcategory;

@Repository
public class CategoryDaoImp implements CategoryDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	public CategoryDaoImp(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void save(Productcategory category) {
		entityManager.persist(category);		
	}

	@Override
	public void update(Productcategory category) {
		entityManager.merge(category);
	}

	@Override
	public void delete(Productcategory category) {
		entityManager.remove(category);
	}

	@Override
	public List<Productcategory> findAll() {
		String request = "Select c from Productcategory c";
		return 	entityManager.createQuery(request).getResultList();
	}

	@Override
	public Productcategory findById(Integer id) {
		// TODO Auto-generated method stub
		return entityManager.find(Productcategory.class, id);
	}

}
