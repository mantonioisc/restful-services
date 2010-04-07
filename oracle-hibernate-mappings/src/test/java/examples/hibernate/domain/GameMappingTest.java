package examples.hibernate.domain;

import java.util.List;

import org.hibernate.Transaction;
import org.junit.Test;

public class GameMappingTest extends BaseMappingTest {
	@SuppressWarnings("unchecked")
	@Test
	public void testGameMapping(){
		Transaction transaction = session.beginTransaction();
		
		List<Game> games = session.createQuery("from Game").list();
		for(Game game:games){
			System.out.println(game);
		}
		
		transaction.commit();
	}
}
