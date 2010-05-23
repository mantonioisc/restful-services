package examples.hibernate.dao.spring.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import examples.hibernate.dao.UserDao;
import examples.hibernate.domain.Console;
import examples.hibernate.domain.Game;
import examples.hibernate.domain.User;
import examples.hibernate.domain.join.UserConsole;
import examples.hibernate.domain.join.UserGame;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	@Override
	public void addUser(User user) {
		getHibernateTemplate().save(user);
	}

	@Override
	public void deleteUser(int id) {
		User user = (User)getHibernateTemplate().get(User.class, id);
		getHibernateTemplate().delete(user);
	}

	@Override
	public User getUser(int id) {
		return (User)getHibernateTemplate().get(User.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Console> getUserConsoles(int userId) {
		List<UserConsole> userConsoles = getHibernateTemplate().find("from UserConsole uc where uc.user.id = ?", userId);
		List<Console> consoles = new ArrayList<Console>();
		for(UserConsole userConsole:userConsoles){
			consoles.add(userConsole.getConsole());
		}
		return consoles;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Game> getUserGames(int userId) {
		List<UserGame> userGames = getHibernateTemplate().findByNamedParam("from UserGame ug where ug.user.id = :userId", "userId", userId);
		List<Game> games = new ArrayList<Game>();
		for(UserGame userGame:userGames){
			games.add(userGame.getGame());
		}
		return games;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsers() {
		return getHibernateTemplate().find("from User");
	}

	@Override
	public void updateUser(User user) {
		getHibernateTemplate().update(user);
	}

}
