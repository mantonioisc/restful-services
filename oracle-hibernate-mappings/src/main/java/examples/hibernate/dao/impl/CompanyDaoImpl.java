package examples.hibernate.dao.impl;

import java.util.Collections;
import java.util.List;

import org.hibernate.Session;

import examples.hibernate.dao.CompanyDao;
import examples.hibernate.domain.Company;
import examples.hibernate.util.HibernateUtil;

public class CompanyDaoImpl implements CompanyDao {

	public void addCompany(Company company) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try{
			session.beginTransaction();
			session.save(company);
			session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Company> getCompanies() {
		List<Company> companies = Collections.emptyList();
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			companies = session.createQuery("from Company").list();
			session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
		}
		
		return companies;
	}

	public Company getCompany(int id) {
		Company company = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			company = (Company)session.get(Company.class, id);
			session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
		}
		return company;
	}

	public void updateCompany(Company company) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			session.update(company);
			session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
		}
	}

}
