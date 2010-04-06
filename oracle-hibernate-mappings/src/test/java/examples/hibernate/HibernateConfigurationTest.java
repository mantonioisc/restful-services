package examples.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import static org.junit.Assert.*;


public class HibernateConfigurationTest {
	@Test
	public void testConfigurationLoading(){
		SessionFactory sessionFactory = new Configuration().configure("games.cfg.xml").buildSessionFactory();
		assertNotNull(sessionFactory);
	}
}
