package examples.hibernate.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import examples.hibernate.dao.GameDao;
import examples.hibernate.domain.Game;
import examples.hibernate.util.HibernateUtil;

public class GameDaoImpl implements GameDao {

	public void addGame(Game game) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.getTransaction();
		try{
			transaction.begin();
			session.save(game);
			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
		}
	}

	public Game getGame(String code) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.getTransaction();
		Game game = null;
		try{
			transaction.begin();
			game = (Game)session.get(Game.class, code);
			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
		}
		return game;
	}

	@SuppressWarnings("unchecked")
	public List<Game> getGames() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.getTransaction();
		List<Game> games = Collections.emptyList();
		try{
			transaction.begin();
			games = session.createQuery("from Game").list();
			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
		}
		return games;
	}

	@SuppressWarnings("unchecked")
	public List<Game> getGamesByConsole(int consoleId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.getTransaction();
		List<Game> games = new ArrayList<Game>();
		try{
			transaction.begin();
			
			Query query = 
				session.createQuery("from Game g join g.consoles c where c.id = :consoleId ").setInteger("consoleId", consoleId);
			Iterator<Object[]> pairs = query.list().iterator();
			while(pairs.hasNext()){
				Object[] pair = pairs.next();
				games.add((Game)pair[0]);
			}
			
			transaction.commit();
		}catch (Exception e) {
			transaction.rollback();
		}
		return games;
	}

	@SuppressWarnings("unchecked")
	public List<Game> getGamesByDeveloper(int developerId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.getTransaction();
		List<Game> games = null;
		try{
			transaction.begin();
			games = 
				session.createQuery("from Game g where g.developer.id = :developerId").setInteger("developerId", developerId).list();
			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
		}
		return games;
	}

	public void updateGame(Game game) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.getTransaction();
		try{
			transaction.begin();
			session.update(game);
			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
		}
	}

	/**
	 * This implementation works like and logical OR inclusive
	 */
	@SuppressWarnings("unchecked")
	public List<Game> getGamesByTags(List<String> tags) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.getTransaction();
		Set<Game> games = new HashSet<Game>();//remove duplicates
		try{
			transaction.begin();
			
			Query query = session.createQuery("from Game g join g.tags t where t.name in (:tags)").setParameterList("tags", tags);
			Iterator<Object[]> pairs = query.list().iterator();
			while(pairs.hasNext()){
				Object[] pair = pairs.next();
				games.add((Game)pair[0]);
			}
			
			transaction.rollback();
		}catch(Exception e){
			transaction.rollback();
		}
		return new ArrayList<Game>(games);
	}

}
