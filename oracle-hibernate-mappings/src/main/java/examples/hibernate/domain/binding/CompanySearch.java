package examples.hibernate.domain.binding;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import examples.hibernate.domain.Company;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "http://domain.examples")
@XmlRootElement(name = "companies", namespace = "http://domain.examples")
public class CompanySearch {
	@XmlElement(name = "company", required = true)
	private List<Company> companies = new ArrayList<Company>();
	
	public CompanySearch(){
		
	}

	public CompanySearch(List<Company> companies) {
		super();
		this.companies = companies;
	}

	public List<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}
		
}
