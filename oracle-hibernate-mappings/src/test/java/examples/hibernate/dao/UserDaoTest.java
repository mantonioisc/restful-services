package examples.hibernate.dao;

import java.util.List;

import org.junit.Test;

import examples.hibernate.dao.impl.UserDaoImpl;
import examples.hibernate.domain.Console;
import examples.hibernate.domain.Game;

import static org.junit.Assert.*;

public class UserDaoTest extends BaseDaoTest {
	UserDao dao = new UserDaoImpl();
	
	@Test
	public void testGetUserGames(){
		int userId = 1;
		List<Game> games = dao.getUserGames(userId);
		assertNotNull(games);
		assertFalse(games.isEmpty());
		for(Game game:games){
			System.out.println(game);
		}
	}
	
	@Test
	public void testGetUserConsoles(){
		int userId = 1;
		List<Console> consoles = dao.getUserConsoles(userId);
		assertNotNull(consoles);
		assertFalse(consoles.isEmpty());
		for(Console console:consoles){
			System.out.println(console);
		}
	}
}
