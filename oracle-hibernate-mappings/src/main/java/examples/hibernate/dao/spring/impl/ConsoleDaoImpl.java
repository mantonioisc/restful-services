package examples.hibernate.dao.spring.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import examples.hibernate.dao.ConsoleDao;
import examples.hibernate.domain.Console;

public class ConsoleDaoImpl extends HibernateDaoSupport implements ConsoleDao {

	@Override
	public void addConsole(Console console) {
		getHibernateTemplate().save(console);
	}

	@Override
	public Console getConsole(int id) {
		return (Console)getHibernateTemplate().get(Console.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Console> getConsoles() {
		return getHibernateTemplate().find("from Console");
	}

	@Override
	public void updateConsole(Console console) {
		getHibernateTemplate().update(console);
	}

}
