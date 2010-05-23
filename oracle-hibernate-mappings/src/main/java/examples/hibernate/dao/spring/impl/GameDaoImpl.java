package examples.hibernate.dao.spring.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import examples.hibernate.dao.GameDao;
import examples.hibernate.domain.Game;

public class GameDaoImpl extends HibernateDaoSupport implements GameDao {

	@Override
	public void addGame(Game game) {
		getHibernateTemplate().save(game);
	}

	@Override
	public Game getGame(String code) {
		return (Game)getHibernateTemplate().get(Game.class, code);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Game> getGames() {
		return getHibernateTemplate().find("from Game");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Game> getGamesByConsole(final int consoleId) {
		//return getHibernateTemplate().findByNamedParam("from Game g join g.consoles c where c.id = :consoleId ", "consoleId", consoleId);
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				List<Game> games = new ArrayList<Game>();
				
				Query query = 
					session.createQuery("from Game g join g.consoles c where c.id = :consoleId ").setInteger("consoleId", consoleId);
				Iterator<Object[]> pairs = query.list().iterator();
				while(pairs.hasNext()){
					Object[] pair = pairs.next();
					games.add((Game)pair[0]);
				}
				
				return games;
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Game> getGamesByDeveloper(int developerId) {
		return getHibernateTemplate().
			findByNamedParam("from Game g where g.developer.id = :developerId", "developerId", developerId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Game> getGamesByTags(final List<String> tags) {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				List<Game> games = new ArrayList<Game>();
				
				Query query = 
					session.createQuery("from Game g join g.tags t where t.name in (:tags)").setParameterList("tags", tags);
				Iterator<Object[]> pairs =  query.list().iterator();
				while(pairs.hasNext()){
					Object[] pair = pairs.next();
					games.add((Game)pair[0]);
				}
				
				return games;
			}
		});
	}

	@Override
	public void updateGame(Game game) {
		getHibernateTemplate().update(game);
	}

}
