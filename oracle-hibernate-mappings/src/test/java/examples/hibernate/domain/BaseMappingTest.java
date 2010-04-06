package examples.hibernate.domain;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public abstract class BaseMappingTest {
	protected static SessionFactory sessionFactory;
	
	protected Session session;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		sessionFactory = new Configuration().configure("games.cfg.xml").buildSessionFactory();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		sessionFactory.close();
	}
	
	@Before
	public void setUp(){
		session = sessionFactory.openSession();
	}

	@After
	public void tearDown(){
		session.close();
	}
}
