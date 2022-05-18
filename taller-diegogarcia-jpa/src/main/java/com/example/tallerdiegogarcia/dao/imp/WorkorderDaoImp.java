package com.example.tallerdiegogarcia.dao.imp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.example.tallerdiegogarcia.dao.interfaces.WorkOrderDao;
import com.example.tallerdiegogarcia.model.Product;
import com.example.tallerdiegogarcia.model.Workorder;

@Repository
public class WorkorderDaoImp implements WorkOrderDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public WorkorderDaoImp(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void save(Workorder wr) {
		entityManager.persist(wr);
	}

	@Override
	public void update(Workorder wr) {
		// TODO Auto-generated method stub
		entityManager.merge(wr);
	}

	@Override
	public void delete(Workorder wr) {
		entityManager.remove(entityManager.contains(wr) ? wr : entityManager.merge(wr));
	}

	@Override
	public List<Workorder> findAll() {
		String request = "Select w from Workorder w";
		return 	entityManager.createQuery(request).getResultList();
	}

	@Override
	public Workorder findById(Integer id) {
		// TODO Auto-generated method stub
		return entityManager.find(Workorder.class, id);
	}

	@Override
	public List<Workorder> findByScrapreason(Integer id) {
		String request = "SELECT w FROM Workorder w WHERE w.scrapreason.scrapreasonid = :id";
		TypedQuery<Workorder> query = entityManager.createQuery(request, Workorder.class);
		return 	query.setParameter("id", id).getResultList();	
	}

	@Override
	public List<Workorder> findByQuantity(Integer qty) {
		String request = "SELECT w FROM Workorder w WHERE w.scrappedqty = :qty";
		TypedQuery<Workorder> query = entityManager.createQuery(request, Workorder.class);
		return 	query.setParameter("qty", qty).getResultList();
	}

}
