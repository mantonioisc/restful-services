package examples.hibernate.domain.join;

import java.io.Serializable;
import java.util.Date;

import examples.hibernate.domain.Game;
import examples.hibernate.domain.User;

/**
 * Since the join table between USERS and GAMES has columns by itself hibernate
 * recommends make a new entity that has both of the independent entities and
 * the properties that maps to the other columns. If we use a many-to-many
 * mapping between the original entities the join table data will be lost.
 */
public class UserGame {
	/**
	 * A class to map a composite key
	 */
	public static class Id implements Serializable{
		private static final long serialVersionUID = -4756174184142299926L;
		private int userId;
		private String gameCode;
		
		public Id(){
			
		}
		public Id(int userId, String gameCode){
			this.userId = userId;
			this.gameCode = gameCode;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((gameCode == null) ? 0 : gameCode.hashCode());
			result = prime * result + userId;
			return result;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Id other = (Id) obj;
			if (gameCode == null) {
				if (other.gameCode != null)
					return false;
			} else if (!gameCode.equals(other.gameCode))
				return false;
			if (userId != other.userId)
				return false;
			return true;
		}
		
	}
	
	private Id id = new Id();
	private User user;
	private Game game;
	private Date acquiredDate;
	/**
	 * 'NEW','USED'
	 */
	private String acquiredCondition;
	/**
	 * 'OPENED','BRANDNEW'
	 */
	private String currentCondition;
	private boolean greatestHits;
	
	public Id getId() {
		return id;
	}
	public void setId(Id id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public Date getAcquiredDate() {
		return acquiredDate;
	}
	public void setAcquiredDate(Date acquiredDate) {
		this.acquiredDate = acquiredDate;
	}
	public String getAcquiredCondition() {
		return acquiredCondition;
	}
	public void setAcquiredCondition(String acquiredCondition) {
		this.acquiredCondition = acquiredCondition;
	}
	public String getCurrentCondition() {
		return currentCondition;
	}
	public void setCurrentCondition(String currentCondition) {
		this.currentCondition = currentCondition;
	}
	public boolean isGreatestHits() {
		return greatestHits;
	}
	public void setGreatestHits(boolean greatestHits) {
		this.greatestHits = greatestHits;
	}
	
}
