package examples.jaxrs.resource;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import examples.hibernate.dao.DeveloperDao;
import examples.hibernate.domain.Developer;
import examples.hibernate.domain.binding.DeveloperSearch;

@Path("/developer")
public class DeveloperResource {
	@Context
	ServletContext servletContext;
	
	@GET
	@Produces("application/xml")
	public DeveloperSearch getDevelopers(){
		return new DeveloperSearch(getDeveloperDao().getDevelopers());
	}
	
	@GET
	@Path("{developerId}")
	@Produces("application/xml")
	public Developer getDeveloper(@PathParam("developerId") int developerId){
		return getDeveloperDao().getDeveloper(developerId);
	}
	
	private DeveloperDao getDeveloperDao(){
		WebApplicationContext wac =
			(WebApplicationContext)WebApplicationContextUtils.getWebApplicationContext(servletContext);
		return (DeveloperDao)wac.getBean("developerDao");
	}
}
