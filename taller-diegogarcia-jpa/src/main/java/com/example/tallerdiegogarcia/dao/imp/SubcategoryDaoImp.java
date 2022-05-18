package com.example.tallerdiegogarcia.dao.imp;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.example.tallerdiegogarcia.dao.interfaces.SubcategoryDao;
import com.example.tallerdiegogarcia.model.Product;
import com.example.tallerdiegogarcia.model.Productsubcategory;

@Repository
public class SubcategoryDaoImp implements SubcategoryDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public SubcategoryDaoImp(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void save(Productsubcategory prod) {
		entityManager.persist(prod);		
	}

	@Override
	public void update(Productsubcategory prod) {
		// TODO Auto-generated method stub
		entityManager.merge(prod);
	}

	@Override
	public void delete(Productsubcategory prod) {
		// TODO Auto-generated method stub
		entityManager.remove(entityManager.contains(prod) ? prod : entityManager.merge(prod));
	}

	@Override
	public List<Productsubcategory> findAll() {
		String request = "Select s from Productsubcategory s";
		return 	entityManager.createQuery(request).getResultList();
	}

	@Override
	public Productsubcategory findById(Integer id) {
		// TODO Auto-generated method stub
		return entityManager.find(Productsubcategory.class, id);
	}

	@Override
	public List<Productsubcategory> findByName(String name) {
		// TODO Auto-generated method stub
		String request = "SELECT s FROM Productsubcategory s WHERE s.name = :name";
		TypedQuery<Productsubcategory> query = entityManager.createQuery(request, Productsubcategory.class);
		return 	query.setParameter("name", name).getResultList();
	}

	@Override
	public List<Productsubcategory> findByCategory(Integer id) {
		String request = "SELECT s FROM Productsubcategory s WHERE s.productcategory.productcategoryid = :id";
		TypedQuery<Productsubcategory> query = entityManager.createQuery(request, Productsubcategory.class);
		return 	query.setParameter("id", id).getResultList();
	}

	@Override
	public List<Object[]> findbyDateAndCategories(Integer categoryId, LocalDate sellstartdate, LocalDate sellenddate) {
		String request = "SELECT ps,ps.products FROM Productsubcategory ps "
				+ "WHERE ps.productcategory.productcategoryid = :categoryId AND psc.productsubcategoryid ="
				+ "(SELECT p.productsubcategory.productsubcategoryid FROM Product p WHERE (p.sellstartdate BETWEEN :sellstartdate AND :sellenddate))"+
			"AND (p.sellenddate BETWEEN :sellstartdate AND :sellenddate)";
		TypedQuery<Object[]> query = entityManager.createQuery(request,Object[].class);
        query.setParameter("categoryId", categoryId);
        query.setParameter("sellstartdate", sellstartdate);
        query.setParameter("sellenddate", sellenddate);
        List<Object[]> results =query.getResultList(); 
        return  results;
	}

	


}
