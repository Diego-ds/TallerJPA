package com.example.tallerdiegogarcia.dao.interfaces;

import java.util.List;

import com.example.tallerdiegogarcia.model.Workorder;

public interface WorkOrderDao {
	public void save(Workorder wr);
	public void update(Workorder wr);
	public void delete(Workorder wr);
	public List<Workorder> findAll();
	public Workorder findById(Integer id);
	public List<Workorder> findByScrapreason(Integer id);
	public List<Workorder> findByQuantity(Integer qty);
}
