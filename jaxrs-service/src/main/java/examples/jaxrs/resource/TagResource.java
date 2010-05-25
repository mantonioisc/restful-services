package examples.jaxrs.resource;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import examples.hibernate.dao.TagDao;
import examples.hibernate.domain.Tag;
import examples.hibernate.domain.binding.TagSearch;

/**
 * Another approach using {@link ServletContext} to get reference to
 * the {@link WebApplicationContext}
 */
@Path("/tag")
public class TagResource {
	@Context
	private ServletContext servletContext;
	
	@GET
	@Produces("application/xml")
	public TagSearch getTags(){
		return new TagSearch(getTagDao().getTags());
	}
	
	@GET
	@Path("{name}")
	@Produces("application/xml")
	public Tag getTag(@PathParam("name") String name){
		return getTagDao().getTag(name);
	}
	
	
	/**
	 * Utility method to avoid getting the {@link TagDao} reference in every
	 * method.
	 * @return a {@link TagDao} from the spring application context
	 */
	protected TagDao getTagDao(){
		WebApplicationContext wac = 
			WebApplicationContextUtils.getWebApplicationContext(servletContext);
		return (TagDao)wac.getBean("tagDao");
	}
}
