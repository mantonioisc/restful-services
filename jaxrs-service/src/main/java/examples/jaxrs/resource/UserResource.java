package examples.jaxrs.resource;

import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.jboss.resteasy.plugins.spring.SpringBeanProcessorServletAware;
import org.jboss.resteasy.spi.Registry;

import examples.hibernate.dao.UserDao;
import examples.hibernate.domain.User;
import examples.hibernate.domain.binding.UserSearch;

/**
 * Yet another approach, but 100% dependent in RESTEasy. Using RESTEasy-
 * spring integration. The beans configured in spring can be registered
 * with a BeanPostProcesor, or we can register manually with the {@link Registry} 
 * using the support class {@link SpringBeanProcessorServletAware} that
 * give us an instance of it, then we register this resource class as
 * a singleton(just one instance in all the application) to avoid the
 * multiple runs of {@link #init()}. <br>
 * This way it doesn't need to be registered in web.xml context param
 * <code>resteasy.resources</code>
 * <br>
 * But we also need an application context for this application and
 * declare it web.xml <code>contextConfigLocations</code> context param.
 * <br>
 * Also this illustrates the use of {@link PostConstruct} annotation as
 * an alternative to InitializingBean interface or init-method parameter
 * in bean configuration.
 * <br>
 * See 
 * <a href"http://docs.jboss.org/resteasy/docs/1.2.GA/userguide/html/RESTEasy_Spring_Integration.html">
 * from JBoss documentation</a>
 */
@Path("/user")
public class UserResource extends SpringBeanProcessorServletAware {
	private UserDao userDao;
	
	@PostConstruct
	public void init(){
		//register this class when spring loads the bean
		getRegistry().addSingletonResource(UserResource.class);
	}
	
	@GET
	@Produces("application/xml")
	public UserSearch getUsers(){
		return new UserSearch(userDao.getUsers());
	}
	
	@GET
	@Path("{userId}")
	@Produces("application/xml")
	public User getUser(@PathParam("userId") int userId){
		return userDao.getUser(userId);
	}

	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}
