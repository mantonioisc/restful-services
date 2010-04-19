package examples.hibernate.dao.impl;

import java.util.Collections;
import java.util.List;

import org.hibernate.Session;

import examples.hibernate.dao.ConsoleDao;
import examples.hibernate.domain.Console;
import examples.hibernate.util.HibernateUtil;

public class ConsoleDaoImpl implements ConsoleDao {

	public void addConsole(Console console) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			session.save(console);
			session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
		}

	}

	public Console getConsole(int id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Console console = null;
		try{
			session.beginTransaction();
			console = (Console)session.get(Console.class, id);
			session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
		}
		return console;
	}

	@SuppressWarnings("unchecked")
	public List<Console> getConsoles() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<Console> consoles = Collections.emptyList();
		try{
			session.beginTransaction();
			consoles = session.createQuery("from Console").list();
			session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
		}
		return consoles;
	}

	public void updateConsole(Console console) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			session.update(console);
			session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
		}
	}

}
