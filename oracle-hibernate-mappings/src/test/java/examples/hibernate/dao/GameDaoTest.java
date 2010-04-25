package examples.hibernate.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import examples.hibernate.dao.impl.GameDaoImpl;
import examples.hibernate.domain.Game;

import static org.junit.Assert.*;

public class GameDaoTest extends BaseDaoTest {
	GameDao dao = new GameDaoImpl();
	
	@Test
	public void testGetGames(){
		List<Game> games = dao.getGames();
		assertNotNull(games);
		assertFalse(games.isEmpty());
		for(Game game:games){
			System.out.println(game);	
		}
	}
	
	@Test
	public void testGetGamesByConsole(){
		int consoleId = 2;//ps3
		List<Game> games = dao.getGamesByConsole(consoleId);
		assertNotNull(games);
		assertFalse(games.isEmpty());
		for(Game game:games){
			System.out.println(game);	
		}
	}
	@Test
	public void testGetGamesByDeveloper(){
		int developerId = 1;
		List<Game> games = dao.getGamesByDeveloper(developerId);
		assertNotNull(games);
		assertFalse(games.isEmpty());
		for(Game game:games){
			System.out.println(game);	
		}
	}
	
	@Test
	public void testGetGamesByTags(){
		List<String> tags = new ArrayList<String>();
		tags.add("exclusive");
		tags.add("1080p");
		List<Game> games = dao.getGamesByTags(tags);
		assertNotNull(games);
		assertFalse(games.isEmpty());
		for(Game game:games){
			System.out.println(game);	
		}
	}
}
