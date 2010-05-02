package examples.restlet.resource;

import java.util.Collections;
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
import org.restlet.resource.ResourceException;
import org.restlet.resource.Variant;
import org.w3c.dom.Document;

import examples.hibernate.dao.GameDao;
import examples.hibernate.dao.UserDao;
import examples.hibernate.dao.impl.GameDaoImpl;
import examples.hibernate.dao.impl.UserDaoImpl;
import examples.hibernate.domain.Console;
import examples.hibernate.domain.Game;
import examples.hibernate.domain.binding.GameSearch;

public class GameResource extends Resource {
	private GameDao gameDao = new GameDaoImpl();
	private UserDao userDao = new UserDaoImpl();

	public GameResource(Context context, Request request, Response response) {
		super(context, request, response);
		
		setModifiable(true);
		
		getVariants().add(new Variant(MediaType.APPLICATION_XML));
	}

	@Override
	public Representation represent(Variant variant) throws ResourceException {
		Representation representation = null;
		try{
			if(MediaType.APPLICATION_XML.equals(variant.getMediaType())){
				Object search = null;
				Map<String, Object> attributes = getRequest().getAttributes();
				String gameCode = (String)attributes.get("code");
				String developerId = (String)attributes.get("developerId");
				String consoleId = (String)attributes.get("consoleId");
				String userId = (String)attributes.get("userId");
				String tagName = (String)attributes.get("tagName");
				if(gameCode!=null){
					Game game = gameDao.getGame(gameCode);
					for(Console console:game.getConsoles()){
						console.getCompany().setConsoles(null);
					}
					search = game;
				}else if(developerId!=null){
					int id = Integer.parseInt(developerId);
					List<Game> games = gameDao.getGamesByDeveloper(id);
					for(Game game:games){
						for(Console console:game.getConsoles()){
							console.getCompany().setConsoles(null);
						}
					}
					search = new GameSearch(games);
				}else if(consoleId!=null){
					int id = Integer.parseInt(consoleId);
					List<Game> games = gameDao.getGamesByConsole(id);
					for(Game game:games){
						for(Console console:game.getConsoles()){
							console.getCompany().setConsoles(null);
						}
					}
					search = new GameSearch(games);
				}else if(userId!=null){
					int id = Integer.parseInt(userId);
					List<Game> games = userDao.getUserGames(id);
					for(Game game:games){
						for(Console console:game.getConsoles()){
							console.getCompany().setConsoles(null);
						}
					}
					search = new GameSearch(games);
				}else if(tagName!=null){
					List<Game> games = 
						gameDao.getGamesByTags(Collections.singletonList(tagName));
					for(Game game:games){
						for(Console console:game.getConsoles()){
							console.getCompany().setConsoles(null);
						}
					}
					search = new GameSearch(games);
				}else{
					List<Game> games = gameDao.getGames();
					for(Game game:games){
						for(Console console:game.getConsoles()){
							console.getCompany().setConsoles(null);
						}
					}
					search = new GameSearch(games);
				}
				
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = dbf.newDocumentBuilder();
				Document doc = builder.newDocument();
				
				JAXBContext context = JAXBContext.newInstance(Game.class, GameSearch.class);
				Marshaller m = context.createMarshaller();
				m.setProperty("jaxb.formatted.output", true);
				m.marshal(search, doc);
				
				representation = new DomRepresentation(MediaType.APPLICATION_XML, doc);
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new ResourceException(Status.SERVER_ERROR_INTERNAL, e);
		}
		
		return representation;
	}
	
	

}
