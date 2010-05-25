package examples.jaxrs.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import examples.hibernate.dao.UserDao;
import examples.hibernate.domain.User;
import examples.hibernate.domain.binding.UserSearch;

/**
 * @version 2.0 Didn't work with RESTEasy-spring integration when
 * deploying inside a war.
 */
@Path("/user")
public class UserResource extends SpringBeanAutowiringSupport{
	@Autowired
	private UserDao userDao;
	
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
}
