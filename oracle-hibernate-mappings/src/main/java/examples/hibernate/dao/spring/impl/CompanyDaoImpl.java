package examples.hibernate.dao.spring.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import examples.hibernate.dao.CompanyDao;
import examples.hibernate.domain.Company;

public class CompanyDaoImpl extends HibernateDaoSupport implements CompanyDao {

	@Override
	public void addCompany(Company company) {
		getHibernateTemplate().save(company);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Company> getCompanies() {
		return getHibernateTemplate().find("from Company");
	}

	@Override
	public Company getCompany(int id) {
		return (Company)getHibernateTemplate().get(Company.class, id);
	}

	@Override
	public void updateCompany(Company company) {
		getHibernateTemplate().update(company);
	}

}
