package examples.hibernate.dao;

import java.util.List;

import examples.hibernate.domain.Company;

public interface CompanyDao {
	public void addCompany(Company company);
	public void updateCompany(Company company);
	public Company getCompany(int id);
	public List<Company> getCompanies();
}
