package examples.jaxrs.resource;

import java.util.Collections;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import examples.hibernate.dao.GameDao;
import examples.hibernate.dao.UserDao;
import examples.hibernate.domain.Console;
import examples.hibernate.domain.Game;
import examples.hibernate.domain.binding.GameSearch;

@Path("/game")
public class GameResource extends SpringBeanAutowiringSupport {
	@Autowired
	private GameDao gameDao;
	@Autowired
	private UserDao userDao;
	
	@GET
	@Produces("application/xml")
	public GameSearch getGames(){
		List<Game> games = gameDao.getGames();
		prepareForMarshaling(games);
		return new GameSearch(games);
	}
	
	@GET
	@Path("{code}")
	@Produces("application/xml")
	public Game getGame(@PathParam("code") String sku){
		Game game = gameDao.getGame(sku);
		for(Console console:game.getConsoles()){
			console.getCompany().setConsoles(null);
		}
		return game;
	}
	
	@GET
	@Path("/developer/{developerId}")
	@Produces("application/xml")
	public GameSearch getGamesByDeveloper(@PathParam("developerId") int developerId){
		List<Game> games = gameDao.getGamesByDeveloper(developerId);
		prepareForMarshaling(games);
		return new GameSearch(games);
	}
	
	@GET
	@Path("/console/{consoleId}")
	@Produces("application/xml")
	public GameSearch getGamesByConsole(@PathParam("consoleId") int consoleId){
		List<Game> games = gameDao.getGamesByConsole(consoleId);
		prepareForMarshaling(games);
		return new GameSearch(games);
	}
	
	@GET
	@Path("/user/{userId}")
	@Produces("application/xml")
	public GameSearch getUserGames(@PathParam("userId") int userId){
		List<Game> games = userDao.getUserGames(userId);
		prepareForMarshaling(games);
		return new GameSearch(games);
	}
	
	@GET
	@Path("/tag/{tagName}")
	@Produces("application/xml")
	public GameSearch getGamesByTag(@PathParam("tagName") String tagName){
		List<Game> games = gameDao.getGamesByTags(Collections.singletonList(tagName));
		prepareForMarshaling(games);
		return new GameSearch(games);
	}
	
	private void prepareForMarshaling(List<Game> games){
		for(Game game:games){
			for(Console console:game.getConsoles()){
				console.getCompany().setConsoles(null);
			}
		}
	}
}
