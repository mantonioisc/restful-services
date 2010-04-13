package examples.hibernate.domain.join;

import java.util.List;

import org.hibernate.Transaction;
import org.junit.Test;

import examples.hibernate.domain.BaseMappingTest;

public class UserConsoleMappingTest extends BaseMappingTest {
	@Test
	public void testUserConsoleMapping(){
		Transaction transaction = session.beginTransaction();
		
		List<UserConsole> userConsoles = session.createQuery("from UserConsole").list();
		for(UserConsole userConsole:userConsoles){
			System.out.println(userConsole.getUser());
			System.out.println(userConsole.getConsole());
			System.out.println();
		}
		
		transaction.commit();
	}
}
