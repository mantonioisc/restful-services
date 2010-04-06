package examples.hibernate.dao;

import java.util.List;

import examples.hibernate.domain.User;

public interface UserDao {
	public List<User> getUsers();
	public User getUser(int id);
	public void addUser(User user);
	public void updateUser(User user);
	public void deleteUser(int id);
}
