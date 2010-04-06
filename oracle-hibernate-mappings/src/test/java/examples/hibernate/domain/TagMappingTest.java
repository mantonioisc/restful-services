package examples.hibernate.domain;

import java.util.List;

import org.hibernate.Transaction;
import org.junit.Test;

public class TagMappingTest extends BaseMappingTest {
	@Test
	public void testTagMapping(){
		Transaction transaction = session.beginTransaction();
		
		List<Tag> tags = session.createQuery("from Tag").list();
		for(Tag tag:tags){
			System.out.println(tag);
		}
		
		transaction.commit();
	}
}
