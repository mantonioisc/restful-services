package examples.restlet.resource;

import java.io.PrintWriter;
import java.io.StringWriter;
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
import org.restlet.resource.StringRepresentation;
import org.restlet.resource.Variant;
import org.w3c.dom.Document;

import examples.hibernate.dao.CompanyDao;
import examples.hibernate.dao.impl.CompanyDaoImpl;
import examples.hibernate.domain.Company;
import examples.hibernate.domain.Console;
import examples.hibernate.domain.binding.CompanySearch;

public class CompanyResource extends Resource {
	private CompanyDao companyDao = new CompanyDaoImpl();

	public CompanyResource(Context context, Request request, Response response) {
		super(context, request, response);
		
		setModifiable(true);
		
		getVariants().add(new Variant(MediaType.APPLICATION_XML));
	}

	@Override
	public Representation represent(Variant variant){
		Representation representation = null;
		
		try{
			if(MediaType.APPLICATION_XML.equals(variant.getMediaType())){
				String companyId = (String)getRequest().getAttributes().get("id");
				Object search = null;
				if(companyId!=null){
					int id = Integer.parseInt(companyId);
					Company company = companyDao.getCompany(id);
					company.setConsoles(null);
					search = company;
				}else{
					List<Company> companies = companyDao.getCompanies();
					for(Company company:companies){
						for(Console console:company.getConsoles()){
							console.setCompany(null);//avoid marshalling errors
						}
					}
					search = new CompanySearch(companies);	
				}
				
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();
				Document doc = db.newDocument();
				
				JAXBContext context = JAXBContext.newInstance(Company.class, CompanySearch.class);
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
