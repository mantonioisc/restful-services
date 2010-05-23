package examples.hibernate.dao.spring;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import examples.hibernate.dao.GameDao;
import examples.hibernate.domain.Game;

@Transactional
public class GameDaoTest extends BaseDaoTest {
	@Resource
	GameDao gameDao;

	@Test
	public void testGetGames(){
		List<Game> games = gameDao.getGames();
		assertNotNull(games);
		assertFalse(games.isEmpty());
		for(Game game:games){
			System.out.println(game);	
		}
	}
	
	@Test
	public void testGetGamesByConsole(){
		int consoleId = 2;//ps3
		List<Game> games = gameDao.getGamesByConsole(consoleId);
		assertNotNull(games);
		assertFalse(games.isEmpty());
		for(Game game:games){
			System.out.println(game);	
		}
	}
	@Test
	public void testGetGamesByDeveloper(){
		int developerId = 1;
		List<Game> games = gameDao.getGamesByDeveloper(developerId);
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
		List<Game> games = gameDao.getGamesByTags(tags);
		assertNotNull(games);
		assertFalse(games.isEmpty());
		for(Game game:games){
			System.out.println(game);	
		}
	}
	
	@Rollback(true)
	@Test
	public void testGameSave(){
		Game game = new Game();
		game.setCode("fakecode");
		game.setTitle("fake game");
		gameDao.addGame(game);
		Game fromDb = gameDao.getGame(game.getCode());
		assertNotNull(fromDb);
		assertEquals(game, fromDb);
	}
}
