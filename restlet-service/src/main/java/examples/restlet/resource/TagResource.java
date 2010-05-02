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

import examples.hibernate.dao.TagDao;
import examples.hibernate.dao.impl.TagDaoImpl;
import examples.hibernate.domain.Tag;
import examples.hibernate.domain.binding.TagSearch;

public class TagResource extends Resource {
	
	private TagDao tagDao = new TagDaoImpl();

	public TagResource(Context context, Request request, Response response) {
		super(context, request, response);
		
		//handle POST, PUT, DELETE
		setModifiable(true);
		
		//calling get method from the constructor ???
		//using this two variants since they are supported with JAXB out of the box
		getVariants().add(new Variant(MediaType.APPLICATION_XML));
		getVariants().add(new Variant(MediaType.APPLICATION_JSON));
	}

	@Override
	public Representation represent(Variant variant){
		Representation representation = null;
		try{
			if(MediaType.APPLICATION_XML.equals(variant.getMediaType())){
				String tagName = (String)getRequest().getAttributes().get("name");
				Object search = null;
				if(tagName!=null){
					search = tagDao.getTag(tagName);
				}else{
					List<Tag> tags = tagDao.getTags();
					search = new TagSearch(tags);
				}
				
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();
				Document doc = db.newDocument();
				
				JAXBContext context = JAXBContext.newInstance(Tag.class, TagSearch.class);
				Marshaller m = context.createMarshaller();
				m.setProperty("jaxb.formatted.output", true);
				m.marshal(search, doc);
				
				representation = new DomRepresentation(MediaType.APPLICATION_XML, doc);
			}else if(MediaType.APPLICATION_JSON.equals(variant.getMediaType())){
				
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
