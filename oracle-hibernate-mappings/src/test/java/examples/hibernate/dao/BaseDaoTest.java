package examples.hibernate.dao;


import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import examples.hibernate.util.HibernateUtil;

public class BaseDaoTest {
	protected SessionFactory sf;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		HibernateUtil.init("games.cfg.xml");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		HibernateUtil.cleanUp();
	}

	@Before
	public void setUp(){
		sf = HibernateUtil.getSessionFactory();
	}
}
