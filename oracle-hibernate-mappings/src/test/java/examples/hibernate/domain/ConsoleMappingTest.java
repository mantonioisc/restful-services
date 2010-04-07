package examples.hibernate.domain;

import java.util.List;

import org.junit.Test;

public class ConsoleMappingTest extends BaseMappingTest {
	@Test
	public void testConsoleMapping(){
		session.beginTransaction();
		
		List<Console> consoles = session.createQuery("from Console").list();
		for(Console console:consoles){
			System.out.println(console);
		}
		
		session.getTransaction().commit();
	}
}
