package com.example.tallerdiegogarcia.dao.imp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.tallerdiegogarcia.dao.interfaces.ProductDao;
import com.example.tallerdiegogarcia.model.Product;

@Repository
public class ProductDaoImp implements ProductDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	public ProductDaoImp(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void save(Product prod) {
		entityManager.persist(prod);		
	}

	@Override
	public void update(Product prod) {
		entityManager.merge(prod);
	}

	@Override
	public void delete(Product prod) {
		// TODO Auto-generated method stub
		entityManager.remove(entityManager.contains(prod) ? prod : entityManager.merge(prod));
	}

	@Override
	public List<Product> findAll() {
		String request = "Select p from Product p";
		return 	entityManager.createQuery(request).getResultList();
	}

	@Override
	public Product findById(Integer id) {
		// TODO Auto-generated method stub
		return entityManager.find(Product.class, id);
	}

	@Override
	public List<Product> findBySubcategoryid(Integer id) {
		String request = "SELECT p FROM Product p WHERE p.productsubcategory.productsubcategoryid= :id";
		TypedQuery<Product> query = entityManager.createQuery(request, Product.class);
		return 	query.setParameter("id", id).getResultList();
	}

	@Override
	public List<Product> findByModel(Integer id) {
		// TODO Auto-generated method stub
		String request = "SELECT p FROM Product p WHERE p.productmodel.productmodelid= :id";
		TypedQuery<Product> query = entityManager.createQuery(request, Product.class);
		return 	query.setParameter("id", id).getResultList();
	}

	@Override
	public List<Product> findBySizeunitmeasure(Integer id) {
		// TODO Auto-generated method stub
		String request = "SELECT p FROM Product p WHERE p.unitmeasure1.unitmeasurecode= :id";
		TypedQuery<Product> query = entityManager.createQuery(request, Product.class);
		return 	query.setParameter("id", id).getResultList();
	}

	@Override
	public List<Product> findByWorkorderQuantity() {
		String request = "SELECT p FROM Product p WHERE SIZE(p.workorders) >= :minimun";
		TypedQuery<Product> query = entityManager.createQuery(request, Product.class);
		return 	query.setParameter("minimun", 2).getResultList();
	}

}
