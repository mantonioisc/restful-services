package examples.hibernate.dao.impl;

import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import examples.hibernate.dao.TagDao;
import examples.hibernate.domain.Tag;
import examples.hibernate.util.HibernateUtil;

public class TagDaoImpl implements TagDao {

	public void addTag(Tag tag) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			session.save(tag);
			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
		}
	}

	public void deleteTag(int id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			Tag tag = (Tag)session.get(Tag.class, id);
			session.delete(tag);
			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
		}
	}

	public Tag getTag(int id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = null;
		Tag tag = null;
		try{
			transaction = session.beginTransaction();
			tag = (Tag)session.get(Tag.class, id);
			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
		}
		return tag;
	}
	
	public Tag getTag(String name) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = null;
		Tag tag = null;
		try{
			transaction = session.beginTransaction();
			tag = 
				(Tag)session.createQuery("from Tag t where t.name = :name").setString("name", name).uniqueResult();
			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
		}
		return tag;
	}

	@SuppressWarnings("unchecked")
	public List<Tag> getTags() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = null;
		List<Tag> tags = Collections.emptyList();
		try{
			transaction = session.beginTransaction();
			tags = session.createQuery("from Tag").list();
			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
		}
		return tags;
	}

	public void updateTag(Tag tag) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			session.update(tag);
			transaction.commit();
		}catch(Exception e){
			if(transaction!=null){
				transaction.rollback();
			}
		}
	}

}
