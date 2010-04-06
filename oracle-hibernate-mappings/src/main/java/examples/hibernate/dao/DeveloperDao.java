package examples.hibernate.dao;

import java.util.List;

import examples.hibernate.domain.Developer;

public interface DeveloperDao {
	public void addDeveloper(Developer developer);
	public void updateDeveloper(Developer developer);
	public Developer getDeveloper(int id);
	public List<Developer> getDevelopers();
}
