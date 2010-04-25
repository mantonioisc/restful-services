package examples.hibernate.dao;

import java.util.List;

import examples.hibernate.domain.Console;
import examples.hibernate.domain.Game;
import examples.hibernate.domain.User;

public interface UserDao {
	public List<User> getUsers();
	public User getUser(int id);
	public void addUser(User user);
	public void updateUser(User user);
	public void deleteUser(int id);
	public List<Console> getUserConsoles(int userId);
	public List<Game> getUserGames(int userId);
}
