package examples.hibernate.domain;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.junit.Test;

public class UserMappingTest extends BaseMappingTest{
	@SuppressWarnings("unchecked")
	@Test
	public void testUserMapping(){
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery("from User");
		List<User> users = (List<User>)query.list();
		for(User user:users){
			System.out.println(user);
		}
		transaction.commit();
	}
}
