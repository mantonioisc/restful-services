package examples.hibernate.dao;

import java.util.List;

import examples.hibernate.domain.Game;

public interface GameDao {
	public void addGame(Game game);
	public void updateGame(Game game);
	public Game getGame(String code);
	public List<Game> getGames();
	public List<Game> getGamesByDeveloper(int developerId);
	public List<Game> getGamesByConsole(int consoleId);
	public List<Game> getGamesByTags(List<String> tags);
}
