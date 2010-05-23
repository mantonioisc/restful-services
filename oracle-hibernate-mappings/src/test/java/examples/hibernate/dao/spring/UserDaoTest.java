package examples.hibernate.dao.spring;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import examples.hibernate.dao.UserDao;
import examples.hibernate.domain.Console;
import examples.hibernate.domain.Game;

public class UserDaoTest extends BaseDaoTest {
	@Resource
	UserDao userDao;
	
	@Test
	public void testGetUserGames(){
		int userId = 1;
		List<Game> games = userDao.getUserGames(userId);
		assertNotNull(games);
		assertFalse(games.isEmpty());
		for(Game game:games){
			System.out.println(game);
		}
	}
	
	@Test
	public void testGetUserConsoles(){
		int userId = 1;
		List<Console> consoles = userDao.getUserConsoles(userId);
		assertNotNull(consoles);
		assertFalse(consoles.isEmpty());
		for(Console console:consoles){
			System.out.println(console);
		}
	}

}
