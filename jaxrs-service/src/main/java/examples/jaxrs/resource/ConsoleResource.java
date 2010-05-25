package examples.jaxrs.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import examples.hibernate.dao.CompanyDao;
import examples.hibernate.dao.ConsoleDao;
import examples.hibernate.dao.UserDao;
import examples.hibernate.domain.Company;
import examples.hibernate.domain.Console;
import examples.hibernate.domain.binding.ConsoleSearch;

/**
 * 
 */
@Path("/console")
public class ConsoleResource extends SpringBeanAutowiringSupport{
	private static final Logger logger = Logger.getLogger(ConsoleResource.class);
	
	@Autowired
	private ConsoleDao consoleDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private CompanyDao companyDao;
	
	@GET
	@Path("{id}")
	@Produces("application/xml")
	public Console getConsole(@PathParam("id") int id){
		logger.debug("getConsole(" + id + ")");
		Console console = consoleDao.getConsole(id);
		console.getCompany().setConsoles(null);
		return console;
	}
	
	@GET
	@Produces("application/xml")
	public ConsoleSearch getConsoles(){
		logger.debug("getConsoles");
		List<Console> consoles = consoleDao.getConsoles();
		for(Console console:consoles){
			console.getCompany().setConsoles(null);
		}
		return new ConsoleSearch(consoles);
	}
	
	@GET
	@Path("/company/{companyId}")
	@Produces("application/xml")
	public ConsoleSearch getConsolesByCompany(@PathParam("companyId") int companyId){
		logger.debug("getConsolesByCompany: " + companyId);
		Company company = companyDao.getCompany(companyId);
		List<Console> consoles = new ArrayList<Console>(company.getConsoles());
		for(Console console:consoles){
			console.getCompany().setConsoles(null);
		}
		return new ConsoleSearch(consoles);
	}
	
	@GET
	@Path("/user/{userId}")
	@Produces("application/xml")
	public ConsoleSearch getConsolesUserConsoles(@PathParam("userId") int userId){
		logger.debug("getConsoleByUser: " + userId);
		List<Console> consoles = userDao.getUserConsoles(userId);
		for(Console console:consoles){
			console.getCompany().setConsoles(null);
		}
		return new ConsoleSearch(consoles);
	}
	
}
