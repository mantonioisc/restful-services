package examples.jaxrs.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;
import org.jboss.resteasy.annotations.providers.jaxb.json.Mapped;
import org.jboss.resteasy.annotations.providers.jaxb.json.XmlNsMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import examples.hibernate.dao.CompanyDao;
import examples.hibernate.domain.Company;
import examples.hibernate.domain.Console;
import examples.hibernate.domain.binding.CompanySearch;

/**
 * This JAX-RS resource uses {@link SpringBeanAutowiringSupport}
 * to inject spring dependencies using {@link Autowired} annotation.
 * Of course the application context is loaded by ContextLoaderListener
 * <br>
 * It's remarkable the simplicity of this implementation compared
 * with the Restlet version.
 * <br>
 * A bad point to mention is that for use of JSON I need vendor
 * annotations making this non portable. Since the default namespace
 * un JAX-B annotations for domain objects contain a namespace in
 * URL form that is not compatible with JSON, and the JSON binding
 * provider cannot transform it.
 */
@Path("/company")
public class CompanyResource extends SpringBeanAutowiringSupport{
	private static final Logger logger = Logger.getLogger(CompanyResource.class);
	
	@Autowired
	private CompanyDao companyDao;
	
	@GET
	@Produces("application/xml")
	public CompanySearch getCompanies(){
		//It's that simple with JAX-RS!!!
		List<Company> companies = companyDao.getCompanies();
		for(Company company:companies){
			for(Console console:company.getConsoles()){
				console.setCompany(null);//avoid marshalling errors
			}
		}
		return new CompanySearch(companies);
	}
	
	@GET
	@Path("{id}")
	@Produces("application/xml")
	public Company getCompany(@PathParam("id") int id){
		//No type convertion for parameter!!!
		Company company = companyDao.getCompany(id);
		for(Console console:company.getConsoles()){
			console.setCompany(null);
		}
		return company;
	}
	
	@GET
	@Produces("application/json")
	@Mapped(namespaceMap={
			@XmlNsMap(namespace="http://domain.examples", jsonName="domainexamples")
	})
	public CompanySearch getCompaniesAsJSON(){
		return getCompanies();
	}
	
	@GET
	@Path("{id}")
	@Produces("application/json")
	@Mapped(namespaceMap={
			@XmlNsMap(namespace="http://domain.examples", jsonName="domainexamples")
	})
	public Company getCompanyAsJSON(@PathParam("id") int id){
		logger.debug("Getting JSON company(" + id + ")");
		return getCompany(id);
	}
}
