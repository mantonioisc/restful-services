package examples.hibernate.dao.impl;

import java.util.Collections;
import java.util.List;

import org.hibernate.Session;

import examples.hibernate.dao.UserDao;
import examples.hibernate.domain.User;
import examples.hibernate.util.HibernateUtil;

public class UserDaoImpl implements UserDao {

	public void addUser(User user) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
		}
	}

	public void deleteUser(int id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			User user = (User)session.get(User.class, id);
			session.delete(user);
		}catch(Exception e){
			session.getTransaction().rollback();
		}
	}

	public User getUser(int id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		User user = null;
		try{
			session.beginTransaction();
			session.get(User.class, id);
		}catch(Exception e){
			session.getTransaction().rollback();
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	public List<User> getUsers() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<User> users = Collections.emptyList();
		try{
			session.beginTransaction();
			users = session.createQuery("from User").list();
			session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
		}
		return users;
	}

	public void updateUser(User user) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			session.update(user);
			session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
		}
	}

}
