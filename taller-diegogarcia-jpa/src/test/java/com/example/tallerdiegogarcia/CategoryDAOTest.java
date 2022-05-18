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
import com.example.tallerdiegogarcia.model.Productcategory;
import com.example.tallerdiegogarcia.model.Productsubcategory;
import com.example.tallerdiegogarcia.repositories.ProductCategoryRepository;

@SpringBootTest
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CategoryDAOTest {
	
	@Autowired
	CategoryDao categoryDao;
	
	@Autowired
	ProductCategoryRepository categoryRepository;
	
	@BeforeEach
	void setUp() throws Exception {
		categoryRepository.deleteAll();
	}

	@Test
	@Order(1)
	public void saveTest() {
		Productcategory pc = new Productcategory();
		pc.setName("Miscelaneos");
		categoryDao.save(pc);
		assertEquals(categoryDao.findById(1).getName(), pc.getName());
	}
	
	@Test
	@Order(2)
	public void updateTest() {
		Productcategory pc = new Productcategory();
		pc.setName("Miscelaneos");
		categoryDao.save(pc);
		Productcategory pc2 = categoryDao.findById(2);
		pc2.setName("newname");
		categoryDao.update(pc2);
		assertEquals(categoryDao.findById(2).getName(), pc2.getName());
	}
	
	@Test
	@Order(3)
	public void deleteTest() {
		Productcategory pc = new Productcategory();
		pc.setName("Miscelaneos");
		categoryDao.save(pc);
		assertNotNull(categoryDao.findById(3));
		categoryDao.delete(pc);
		assertNull(categoryDao.findById(3));
	}
	
	@Test
	@Order(4)
	public void findAllTest() {
		Productcategory pc = new Productcategory();
		pc.setName("Miscelaneos");
		categoryDao.save(pc);
		Productcategory pc2 = new Productcategory();
		pc2.setName("Otros");
		categoryDao.save(pc2);
		
		List<Productcategory> categories = categoryDao.findAll();
		
		assertNotNull(categories);
		assertEquals(categories.size(),2);
		assertTrue(categories.contains(pc));
		assertTrue(categories.contains(pc2));
	}

}
