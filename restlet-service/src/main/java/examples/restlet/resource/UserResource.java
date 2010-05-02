package examples.restlet.resource;

import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.restlet.Context;
import org.restlet.data.MediaType;
import org.restlet.data.Request;
import org.restlet.data.Response;
import org.restlet.data.Status;
import org.restlet.resource.DomRepresentation;
import org.restlet.resource.Representation;
import org.restlet.resource.Resource;
import org.restlet.resource.ResourceException;
import org.restlet.resource.Variant;
import org.w3c.dom.Document;

import examples.hibernate.dao.UserDao;
import examples.hibernate.dao.impl.UserDaoImpl;
import examples.hibernate.domain.User;
import examples.hibernate.domain.binding.UserSearch;

public class UserResource extends Resource {
	private UserDao userDao = new UserDaoImpl();
	
	public UserResource(Context context, Request request, Response response) {
		super(context, request, response);
		
		setModifiable(true);
		
		getVariants().add(new Variant(MediaType.APPLICATION_XML));
	}

	@Override
	public Representation represent(Variant variant) throws ResourceException {
		Representation representation = null;
		try{
			if(MediaType.APPLICATION_XML.equals(variant.getMediaType())){
				String userId = (String)getRequest().getAttributes().get("id");
				Object search = null;
				if(userId!=null){
					int id = Integer.parseInt(userId);
					search = userDao.getUser(id);
				}else{
					List<User> users = userDao.getUsers();
					search = new UserSearch(users);
				}
				
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = dbf.newDocumentBuilder();
				Document document = builder.newDocument();
				
				JAXBContext context = JAXBContext.newInstance(User.class, UserSearch.class);
				Marshaller m = context.createMarshaller();
				m.setProperty("jaxb.formatted.output", true);
				m.marshal(search, document);
				
				representation = new DomRepresentation(MediaType.APPLICATION_XML, document);
			}
		}catch(Exception e){
			throw new ResourceException(Status.SERVER_ERROR_INTERNAL, e);
		}
		return representation;
	}
	
}
