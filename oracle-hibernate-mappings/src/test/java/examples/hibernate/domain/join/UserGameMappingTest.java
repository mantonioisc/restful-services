package examples.hibernate.domain.join;

import java.util.List;

import org.hibernate.Transaction;
import org.junit.Test;

import examples.hibernate.domain.BaseMappingTest;

public class UserGameMappingTest extends BaseMappingTest {
	@Test
	public void testUserGameMapping(){
		Transaction transaction = session.beginTransaction();
		List<UserGame> userGames = session.createQuery("from UserGame").list();
		for(UserGame userGame:userGames){
			System.out.println(userGame.getUser());
			System.out.println(userGame.getGame());
			System.out.println();
		}
		transaction.commit();
	}
}
