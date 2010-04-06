package examples.hibernate.dao;

import java.util.List;

import examples.hibernate.domain.Console;

public interface ConsoleDao {
	public void addConsole(Console console);
	public void updateConsole(Console console);
	public Console getConsole(int id);
	public List<Console> getConsoles();
}
