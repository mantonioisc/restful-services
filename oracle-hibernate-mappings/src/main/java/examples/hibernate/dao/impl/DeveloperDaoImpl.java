package examples.hibernate.dao.impl;

import java.util.Collections;
import java.util.List;

import org.hibernate.Session;

import examples.hibernate.dao.DeveloperDao;
import examples.hibernate.domain.Developer;
import examples.hibernate.util.HibernateUtil;

public class DeveloperDaoImpl implements DeveloperDao {

	public void addDeveloper(Developer developer) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			session.save(developer);
			session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
		}
	}

	public Developer getDeveloper(int id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Developer developer = null;
		try{
			session.beginTransaction();
			developer = (Developer)session.get(Developer.class, id);
			session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
		}
		return developer;
	}

	@SuppressWarnings("unchecked")
	public List<Developer> getDevelopers() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<Developer> developers = Collections.emptyList();
		try{
			session.beginTransaction();
			developers = session.createQuery("from Developer").list();
			session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
		}
		return developers;
	}

	public void updateDeveloper(Developer developer) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			session.update(developer);
			session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
		}
	}

}
