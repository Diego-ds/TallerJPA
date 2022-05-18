package com.example.tallerdiegogarcia;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.tallerdiegogarcia.dao.interfaces.CategoryDao;
import com.example.tallerdiegogarcia.dao.interfaces.ProductDao;
import com.example.tallerdiegogarcia.dao.interfaces.SubcategoryDao;
import com.example.tallerdiegogarcia.dao.interfaces.WorkOrderDao;
import com.example.tallerdiegogarcia.model.Scrapreason;
import com.example.tallerdiegogarcia.model.Workorder;
import com.example.tallerdiegogarcia.repositories.ScrapReasonRepository;
import com.example.tallerdiegogarcia.repositories.WorkOrderRepository;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
class WorkorderDAOTest {
	
	@Autowired
	WorkOrderDao orderDao;
	
	@Autowired
	ProductDao productDao;
	
	@Autowired
	SubcategoryDao subcatDao;
	
	@Autowired
	CategoryDao categoryDao;
	
	@Autowired
	WorkOrderRepository orderRepository;
	
	@Autowired
	ScrapReasonRepository scrapRepository;
	
	@BeforeEach
	void setUp() throws Exception {
		orderRepository.deleteAll();
	}

	@Test
	@Order(1)
	public void saveTest() {
		Workorder w = new Workorder();
		w.setOrderqty(5);
		orderDao.save(w);
		assertEquals(orderDao.findById(1).getOrderqty(),w.getOrderqty());
	}
	@Test
	@Order(2)
	public void updateTest() {
		Workorder w = new Workorder();
		w.setOrderqty(5);
		orderDao.save(w);
		Workorder w2 = orderDao.findById(2);
		w2.setOrderqty(10);
		orderDao.update(w2);
		assertEquals(orderDao.findById(2).getOrderqty(),w2.getOrderqty());
	}
	@Test
	@Order(3)
	public void deleteTest() {
		Workorder w = new Workorder();
		w.setOrderqty(5);
		orderDao.save(w);
		orderDao.delete(w);
		assertNull(orderDao.findById(3));
	}
	@Test
	@Order(4)
	public void findAllTest() {
		Workorder w = new Workorder();
		w.setOrderqty(5);
		orderDao.save(w);
		Workorder w2 = new Workorder();
		w2.setOrderqty(10);
		orderDao.save(w2);
		
		List<Workorder> orders = orderDao.findAll();
		
		assertNotNull(orders);
		assertEquals(orders.size(),2);
		assertTrue(orders.contains(w));
		assertTrue(orders.contains(w2));
	}
	@Test
	@Order(5)
	public void findByScrapreasonTest() {
		Scrapreason sr = new Scrapreason();
		sr.setName("r1");
		scrapRepository.save(sr);
		
		Scrapreason sr2 = new Scrapreason();
		sr2.setName("r2");
		scrapRepository.save(sr2);
		
		Workorder w = new Workorder();
		w.setOrderqty(5);
		w.setScrapreason(scrapRepository.findById(1).get());
		orderDao.save(w);
		
		Workorder w2 = new Workorder();
		w2.setOrderqty(10);
		w2.setScrapreason(scrapRepository.findById(1).get());
		orderDao.save(w2);
		
		Workorder w3 = new Workorder();
		w3.setOrderqty(13);
		w3.setScrapreason(scrapRepository.findById(2).get());
		orderDao.save(w3);
		
		List<Workorder> orders = orderDao.findByScrapreason(1);
		
		assertNotNull(orders);
		assertEquals(orders.size(),2);
		assertTrue(orders.contains(w));
		assertTrue(orders.contains(w2));
		
		List<Workorder> orders2 = orderDao.findByScrapreason(2);
		
		assertNotNull(orders2);
		assertEquals(orders2.size(),1);
		assertTrue(orders2.contains(w3));		
	}
	@Test
	@Order(6)
	public void findByQuantityTest() {
		Workorder w = new Workorder();
		w.setScrappedqty(5);
		orderDao.save(w);
		
		Workorder w2 = new Workorder();
		w2.setScrappedqty(5);
		orderDao.save(w2);
		
		Workorder w3 = new Workorder();
		w3.setScrappedqty(10);
		orderDao.save(w3);
		
		List<Workorder> orders = orderDao.findByQuantity(5);
		
		assertNotNull(orders);
		assertEquals(orders.size(),2);
		assertTrue(orders.contains(w));
		assertTrue(orders.contains(w2));
		
		List<Workorder> orders2 = orderDao.findByQuantity(10);
		
		assertNotNull(orders2);
		assertEquals(orders2.size(),1);
		assertTrue(orders2.contains(w3));			
	}

}
