package examples.hibernate.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.hibernate.Session;
import org.junit.Test;

import examples.hibernate.dao.impl.CompanyDaoImpl;
import examples.hibernate.domain.Company;

public class CompanyDaoTest extends BaseDaoTest {
	CompanyDao dao = new CompanyDaoImpl();
	
	@Test
	public void testAddCompany(){
		Company company = new Company();
		company.setCountry("country");
		company.setFullName("full name");
		company.setName("name");
		company.setWebSite("http://name");
		
		dao.addCompany(company);
		
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		Company fromDb = (Company)session.get(Company.class, company.getId());
		
		assertEquals(company, fromDb);
		
		session.delete(fromDb);
		session.getTransaction().commit();
	}
	
	@Test
	public void testGetCompany(){
		Company company = new Company();
		company.setCountry("country");
		company.setFullName("full name");
		company.setName("name");
		
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		session.save(company);
		session.getTransaction().commit();
		
		Company fromDao = dao.getCompany(company.getId());
		
		assertEquals(company, fromDao);
		
		session = sf.getCurrentSession();
		session.beginTransaction();
		session.delete(company);
		session.getTransaction().commit();
		
	}
	
	@Test
	public void testGetCompanies(){
		List<Company> companies = dao.getCompanies();
		assertNotNull(companies);
		assertFalse(companies.isEmpty());
	}
	
	@Test
	public void testUpdateCompany(){
		Company company = new Company();
		company.setCountry("country");
		company.setFullName("full name");
		company.setName("name");
		
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		session.save(company);
		session.getTransaction().commit();
		
		company.setName("UPDATED");
		
		dao.updateCompany(company);
		
		session = sf.getCurrentSession();
		session.beginTransaction();
		Company fromDb = (Company)session.get(Company.class, company.getId());
		
		assertEquals(company, fromDb);
		
		session.delete(fromDb);
		session.getTransaction().commit();
	}
}
