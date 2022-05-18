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
import com.example.tallerdiegogarcia.dao.interfaces.SubcategoryDao;
import com.example.tallerdiegogarcia.model.Productcategory;
import com.example.tallerdiegogarcia.model.Productsubcategory;
import com.example.tallerdiegogarcia.repositories.ProductSubCategoryRepository;

@SpringBootTest
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SubcategoryDAOTest {
	
	@Autowired
	private SubcategoryDao subcatDao;
	
	@Autowired
	private ProductSubCategoryRepository subcatRepository;
	
	@Autowired
	private CategoryDao categoryDao;
	
	@BeforeEach
	void setUp() throws Exception {
		subcatRepository.deleteAll();
	}

	@Test
	@Order(1)
	public void saveTest() {
		Productsubcategory p = new Productsubcategory();
		p.setName("Asadores");
		subcatDao.save(p);
		assertEquals(subcatDao.findById(1).getName(), p.getName());
	}
	
	@Test
	@Order(2)
	public void updateTest() {
		Productsubcategory p = new Productsubcategory();
		p.setName("Asadores");
		subcatDao.save(p);
		List<Productsubcategory> subs = subcatDao.findAll();
		for(Productsubcategory ps : subs) {
			System.out.println(ps.getProductsubcategoryid());
		}
		Productsubcategory p2 = subcatDao.findById(2);
		p2.setName("Condimentos");
		subcatDao.update(p2);
		assertEquals(subcatDao.findById(2).getName(), p2.getName());	
	}
	
	@Test
	@Order(3)
	public void deleteTest() {
		Productsubcategory p = new Productsubcategory();
		p.setName("todelete");
		subcatDao.save(p);
		subcatDao.delete(p);
		assertNull(subcatDao.findById(3));
	}
	
	@Test
	@Order(4)
	public void findAllTest() {
		Productsubcategory ps = new Productsubcategory();
		ps.setName("n1");
		Productsubcategory ps2 = new Productsubcategory();
		ps2.setName("n2");

		subcatDao.save(ps);
		subcatDao.save(ps2);


		List<Productsubcategory> subs = subcatDao.findAll();


		assertNotNull(subs);
		assertEquals(subs.size(),2);
		assertTrue(subs.contains(ps));
		assertTrue(subs.contains(ps2));
	}
	
	@Test
	@Order(5)
	public void findByNameTest() {
		Productsubcategory p = new Productsubcategory();
		p.setName("mismo");
		Productsubcategory p2 = new Productsubcategory();
		p2.setName("mismo");
		Productsubcategory p3 = new Productsubcategory();
		p3.setName("diff");

		subcatDao.save(p);
		subcatDao.save(p2);
		subcatDao.save(p3);

		List<Productsubcategory> subs = subcatDao.findByName("mismo");

		assertNotNull(subs);
		assertEquals(subs.size(),2);
		assertTrue(subs.contains(p));
		assertTrue(subs.contains(p2));
		
		List<Productsubcategory> subsDiff = subcatDao.findByName("diff");


		assertNotNull(subsDiff);
		assertEquals(subsDiff.size(),1);
		assertTrue(subsDiff.contains(p3));
	}
	
	@Test
	@Order(6)
	public void findByCategoryTest() {
		Productcategory pc = new Productcategory();
		pc.setName("Mascotas");
		categoryDao.save(pc);
		
		Productcategory pc2 = new Productcategory();
		pc.setName("Cosmeticos");
		categoryDao.save(pc2);
		
		Productsubcategory p = new Productsubcategory();
		p.setName("mismo");
		p.setProductcategory(categoryDao.findById(1));
		
		Productsubcategory p2 = new Productsubcategory();
		p2.setName("mismo");
		p2.setProductcategory(categoryDao.findById(2));


		subcatDao.save(p);
		subcatDao.save(p2);
		
		List<Productsubcategory> subs = subcatDao.findByCategory(1);


		assertNotNull(subs);
		assertEquals(subs.size(),1);
		assertTrue(subs.contains(p));
		
		List<Productsubcategory> subs2 = subcatDao.findByCategory(2);


		assertNotNull(subs2);
		assertEquals(subs2.size(),1);
		assertTrue(subs2.contains(p2));
			
	}
	
	@Test
	@Order(7)
	public void findByCategoryAndDatesTest() {
		//
	}

}
