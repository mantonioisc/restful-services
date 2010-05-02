package examples.restlet.resource;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.restlet.Context;
import org.restlet.data.MediaType;
import org.restlet.data.Request;
import org.restlet.data.Response;
import org.restlet.data.Status;
import org.restlet.resource.DomRepresentation;
import org.restlet.resource.Representation;
import org.restlet.resource.Resource;
import org.restlet.resource.StringRepresentation;
import org.restlet.resource.Variant;
import org.w3c.dom.Document;

import examples.hibernate.dao.DeveloperDao;
import examples.hibernate.dao.impl.DeveloperDaoImpl;
import examples.hibernate.domain.Developer;
import examples.hibernate.domain.binding.DeveloperSearch;

public class DeveloperResource extends Resource {
	private DeveloperDao developerDao = new DeveloperDaoImpl();
	
	public DeveloperResource(Context context, Request request, Response response){
		super(context, request, response);
		
		setModifiable(true);
		
		getVariants().add(new Variant(MediaType.APPLICATION_XML));
		getVariants().add(new Variant(MediaType.APPLICATION_JSON));
	}
	
	@Override
	public Representation represent(Variant variant){
		Representation representation = null;
		try{
			if(MediaType.APPLICATION_XML.equals(variant.getMediaType())){
				Object search = null;
				String developerId = (String)getRequest().getAttributes().get("id");
				if(developerId!=null){
					int id = Integer.parseInt(developerId);
					search = developerDao.getDeveloper(id);
				}else{
					List<Developer> developers = developerDao.getDevelopers();
					search = new DeveloperSearch(developers);
				}
				
				
				DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
				Document doc = db.newDocument();
				
				JAXBContext context = JAXBContext.newInstance(Developer.class, DeveloperSearch.class);
				Marshaller m = context.createMarshaller();
				m.marshal(search, doc);
				
				representation = new DomRepresentation(variant.getMediaType(), doc);
			}
		}catch(JAXBException e){
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			getResponse().setStatus(Status.SERVER_ERROR_INTERNAL, e);
			representation = new StringRepresentation(sw.toString());
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		return representation;
	}
}
