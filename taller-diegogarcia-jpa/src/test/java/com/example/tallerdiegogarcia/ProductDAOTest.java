package com.example.tallerdiegogarcia;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.tallerdiegogarcia.dao.interfaces.ProductDao;
import com.example.tallerdiegogarcia.dao.interfaces.SubcategoryDao;
import com.example.tallerdiegogarcia.dao.interfaces.WorkOrderDao;
import com.example.tallerdiegogarcia.model.Product;
import com.example.tallerdiegogarcia.model.Productmodel;
import com.example.tallerdiegogarcia.model.Productsubcategory;
import com.example.tallerdiegogarcia.model.Unitmeasure;
import com.example.tallerdiegogarcia.model.Workorder;
import com.example.tallerdiegogarcia.repositories.ModelRepository;
import com.example.tallerdiegogarcia.repositories.ProductRepository;
import com.example.tallerdiegogarcia.repositories.UnitmeasureRepository;

@SpringBootTest
@Transactional
class ProductDAOTest {

	@Autowired
	private ProductDao productDao;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private SubcategoryDao subcatDao;

	@Autowired
	private UnitmeasureRepository unitRepository;

	@Autowired
	private ModelRepository modelRepository;
	
	@Autowired
	private WorkOrderDao orderDao;

	@BeforeEach
	void setUp() throws Exception {
		productRepository.deleteAll();
	}

	@Test
	public void saveTest() {
		Product p = new Product();
		p.setName("Jabon");
		productDao.save(p);
		assertEquals(productDao.findById(1).getName(), p.getName());
	}

	@Test
	public void updateTest() {
		Product p = new Product();
		p.setName("Jabon");
		productDao.save(p);
		Product p2 = productDao.findById(2);
		p2.setName("Escoba");
		productDao.update(p2);
		assertEquals(productDao.findById(2).getName(), p2.getName());
	}	

	@Test
	public void deleteTest() {
		Product p = new Product();
		p.setName("todelete");
		productDao.save(p);
		productDao.delete(p);
		assertNull(productDao.findById(3));
	}

	@Test
	public void findAllTest() {

		Product p = new Product();
		p.setName("n1");
		Product p2 = new Product();
		p.setName("n2");

		productDao.save(p);
		productDao.save(p2);


		List<Product> products = productDao.findAll();


		assertNotNull(products);
		assertEquals(products.size(),2);
		assertTrue(products.contains(p));
		assertTrue(products.contains(p2));

	}

	@Test
	public void findBySubcategoryid() {
		//Guardamos dos subcategorias
		Productsubcategory ps = new Productsubcategory();
		Productsubcategory ps2 = new Productsubcategory();
		subcatDao.save(ps);
		subcatDao.save(ps2);

		//Creamos un producto asociado a la subcategoria id =1

		Product p = new Product();
		p.setName("p1");
		p.setProductsubcategory(subcatDao.findById(1));
		productDao.save(p);

		//Creamos un producto asociado a la subcategoria id =2

		Product p1 = new Product();
		p1.setName("p2");
		p1.setProductsubcategory(subcatDao.findById(2));
		productDao.save(p1);

		List<Product> products = productDao.findBySubcategoryid(1);

		assertNotNull(products);
		assertEquals(products.size(),1);
		assertTrue(products.contains(p));

		List<Product> products2 = productDao.findBySubcategoryid(2);

		assertNotNull(products2);
		assertEquals(products2.size(),1);
		assertTrue(products2.contains(p1));	
	}

	@Test
	public void findByModel() {
		//Guardamos dos Modelos
		Productmodel m = new Productmodel();
		Productmodel m2 = new Productmodel();
		modelRepository.save(m);
		modelRepository.save(m2);

		//Creamos un producto asociado al modelo id =1

		Product p = new Product();
		p.setName("p1");
		p.setProductmodel(modelRepository.findById(1).get());
		productDao.save(p);

		//Creamos un producto asociado al modelo id =2

		Product p1 = new Product();
		p1.setName("p2");
		p1.setProductmodel(modelRepository.findById(2).get());
		productDao.save(p1);

		List<Product> products = productDao.findByModel(1);

		assertNotNull(products);
		assertEquals(products.size(),1);
		assertTrue(products.contains(p));

		List<Product> products2 = productDao.findByModel(2);

		assertNotNull(products2);
		assertEquals(products2.size(),1);
		assertTrue(products2.contains(p1));	
	}

	@Test
	public void findBySizeunitmeasure() {
		//Guardamos dos Unidades de medida
		Unitmeasure u = new Unitmeasure();
		Unitmeasure u2 = new Unitmeasure();
		unitRepository.save(u);
		unitRepository.save(u2);

		//Creamos un producto asociado a unidad de medida id =1

		Product p = new Product();
		p.setName("p1");
		p.setUnitmeasure1(unitRepository.findById(1).get());
		productDao.save(p);

		//Creamos un producto asociado a unidad medida id =2

		Product p1 = new Product();
		p1.setName("p2");
		p1.setUnitmeasure1(unitRepository.findById(2).get());
		productDao.save(p1);

		List<Product> products = productDao.findBySizeunitmeasure(1);

		assertNotNull(products);
		assertEquals(products.size(),1);
		assertTrue(products.contains(p));

		List<Product> products2 = productDao.findBySizeunitmeasure(2);

		assertNotNull(products2);
		assertEquals(products2.size(),1);
		assertTrue(products2.contains(p1));	
	}

	@Test
	public void findByWorkorderQuantity() {
		
		Product p = new Product();
		p.setName("p1");
		productDao.save(p);
		Product p1 = new Product();
		p1.setName("p2");
		productDao.save(p1);
		
		//Creamos 3 workorders para probar la query
		
		Workorder w = new Workorder();
		w.setStartdate(LocalDate.of(2022, 5,10));
		w.setEnddate(LocalDate.of(2020, 5, 12));
		w.setOrderqty(8);
		w.setScrappedqty(3);
		w.setProduct(p);		
		orderDao.save(w);
		
		Workorder w1 = new Workorder();
		w1.setStartdate(LocalDate.of(2022, 5,10));
		w1.setEnddate(LocalDate.of(2020, 5, 12));
		w1.setOrderqty(8);
		w1.setScrappedqty(3);
		w1.setProduct(p);
		orderDao.save(w1);
		
		Workorder w2 = new Workorder();
		w2.setStartdate(LocalDate.of(2022, 5,10));
		w2.setEnddate(LocalDate.of(2020, 5, 12));
		w2.setOrderqty(8);
		w2.setScrappedqty(3);
		w2.setProduct(p1);
		orderDao.save(w2);
		
		List<Product> products = productDao.findByWorkorderQuantity();
		
		assertNotNull(products);
		assertEquals(products.size(),1);
		assertTrue(products.contains(p));			
	}

}
