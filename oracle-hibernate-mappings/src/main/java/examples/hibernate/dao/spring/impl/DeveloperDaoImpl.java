package examples.hibernate.dao.spring.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import examples.hibernate.dao.DeveloperDao;
import examples.hibernate.domain.Developer;

public class DeveloperDaoImpl extends HibernateDaoSupport implements
		DeveloperDao {

	@Override
	public void addDeveloper(Developer developer) {
		getHibernateTemplate().save(developer);
	}

	@Override
	public Developer getDeveloper(int id) {
		return (Developer)getHibernateTemplate().get(Developer.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Developer> getDevelopers() {
		return getHibernateTemplate().find("from Developer");
	}

	@Override
	public void updateDeveloper(Developer developer) {
		getHibernateTemplate().update(developer);
	}

}
