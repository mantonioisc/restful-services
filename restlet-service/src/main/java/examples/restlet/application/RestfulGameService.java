package examples.restlet.application;

import java.net.URL;

import org.restlet.Application;
import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.Router;

import examples.hibernate.util.HibernateUtil;
import examples.restlet.resource.CompanyResource;
import examples.restlet.resource.ConsoleResource;
import examples.restlet.resource.DeveloperResource;
import examples.restlet.resource.GameResource;
import examples.restlet.resource.TagResource;
import examples.restlet.resource.UserResource;

public class RestfulGameService extends Application {
	
	static{
		try{
			//we need to init hibernate somewhere
			HibernateUtil.init("hibernate.cfg.xml");
		}catch(Exception e){
			throw new ExceptionInInitializerError(e);
		}
	}

	public RestfulGameService(Context context) {
		super(context);
		
	}
	
	@Override
	public Restlet createRoot(){
		Router router = new Router(getContext());
		
		router.attach("/company", CompanyResource.class);
		router.attach("/company/{id}", CompanyResource.class);
		router.attach("/company/{id}/console/{consoleId}", CompanyResource.class);
		
		router.attach("/console", ConsoleResource.class);
		router.attach("/console/{id}", ConsoleResource.class);
		router.attach("/console/company/{companyId}", ConsoleResource.class);
		router.attach("/console/user/{userId}", ConsoleResource.class);
		
		router.attach("/developer", DeveloperResource.class);
		router.attach("/developer/{id}", DeveloperResource.class);
		
		router.attach("/game", GameResource.class);
		router.attach("/game/{code}", GameResource.class);
		router.attach("/game/developer/{developerId}", GameResource.class);
		router.attach("/game/console/{consoleId}", GameResource.class);
		router.attach("/game/user/{userId}", GameResource.class);
		router.attach("/game/tag/{tagName}", GameResource.class);
		router.attach("/game/{code}/console/{consoleId}", GameResource.class);
		router.attach("/game/{code}/tag/{tagName}", GameResource.class);
		
		router.attach("/tag", TagResource.class);
		router.attach("/tag/{name}", TagResource.class);
		
		router.attach("/user", UserResource.class);
		router.attach("/user/{id}", UserResource.class);
		router.attach("/user/{id}/game/{code}", UserResource.class);
		router.attach("/user/{id}/console/{consoleId}", UserResource.class);
		
		return router;
	}

}
