package examples.restlet.resource;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
import org.restlet.resource.StringRepresentation;
import org.restlet.resource.Variant;
import org.w3c.dom.Document;

import examples.hibernate.dao.CompanyDao;
import examples.hibernate.dao.ConsoleDao;
import examples.hibernate.dao.UserDao;
import examples.hibernate.dao.impl.CompanyDaoImpl;
import examples.hibernate.dao.impl.ConsoleDaoImpl;
import examples.hibernate.dao.impl.UserDaoImpl;
import examples.hibernate.domain.Company;
import examples.hibernate.domain.Console;
import examples.hibernate.domain.binding.ConsoleSearch;

public class ConsoleResource extends Resource {
	private ConsoleDao consoleDao = new ConsoleDaoImpl();
	private UserDao userDao = new UserDaoImpl();
	private CompanyDao companyDao = new CompanyDaoImpl();

	public ConsoleResource(Context context, Request request, Response response) {
		super(context, request, response);
		
		setModifiable(true);
		
		getVariants().add(new Variant(MediaType.APPLICATION_XML));
	}
	
	@Override
	public Representation represent(Variant variant){
		Representation representation = null;
		try{
			if(MediaType.APPLICATION_XML.equals(variant.getMediaType())){
				Object search = null;
				Map<String, Object> attributes = getRequest().getAttributes();
				String consoleId = (String)attributes.get("id");
				String companyId = (String)attributes.get("companyId");
				String userId = (String)attributes.get("userId");
				
				if(consoleId!=null){
					int id = Integer.parseInt(consoleId);
					Console console = consoleDao.getConsole(id);
					console.getCompany().setConsoles(null);
					search = console;
				}else if(companyId!=null){
					int id = Integer.parseInt(companyId);
					Company company = companyDao.getCompany(id);
					List<Console> consoles = new LinkedList<Console>(company.getConsoles());
					for(Console console:consoles){
						console.getCompany().setConsoles(null);
					}
					search = new ConsoleSearch(consoles);
				}else if(userId!=null){
					int id = Integer.parseInt(userId);
					List<Console> consoles = userDao.getUserConsoles(id);
					for(Console console:consoles){
						console.getCompany().setConsoles(null);
					}
					search = new ConsoleSearch(consoles);
				}else{
					List<Console> consoles = consoleDao.getConsoles();
					for(Console console:consoles){
						console.getCompany().setConsoles(null);
					}
					search = new ConsoleSearch(consoles);
				}
				
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = dbf.newDocumentBuilder();
				Document doc = builder.newDocument();
				
				JAXBContext context = JAXBContext.newInstance(Console.class, ConsoleSearch.class);
				Marshaller m = context.createMarshaller();
				m.setProperty("jaxb.formatted.output", true);
				m.marshal(search, doc);
				
				representation = new DomRepresentation(MediaType.APPLICATION_XML, doc);
			}
		}catch(Exception e){
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			getResponse().setStatus(Status.SERVER_ERROR_INTERNAL, e);
			representation = new StringRepresentation(sw.toString());
		}
		return representation;
	}

}
