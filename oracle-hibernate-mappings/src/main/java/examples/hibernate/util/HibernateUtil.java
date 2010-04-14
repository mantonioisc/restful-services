package examples.hibernate.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static SessionFactory sessionFactory;
	
	public static void init(String configFile){
		sessionFactory = new Configuration().configure(configFile).buildSessionFactory();
	}
	
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	
	public static void cleanUp(){
		sessionFactory.close();
	}
}
