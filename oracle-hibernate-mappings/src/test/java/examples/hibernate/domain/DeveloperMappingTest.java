package examples.hibernate.domain;

import java.util.List;

import org.junit.Test;

public class DeveloperMappingTest extends BaseMappingTest {
	@SuppressWarnings("unchecked")
	@Test
	public void testDeveloperMapping(){
		session.beginTransaction();
		
		List<Developer> developers = session.createQuery("from Developer").list();
		for(Developer developer:developers){
			System.out.println(developer);
		}
		
		session.getTransaction().commit();
	}
}
