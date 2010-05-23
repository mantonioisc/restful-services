package examples.hibernate.domain.join;

import java.io.Serializable;
import java.util.Date;

import examples.hibernate.domain.Console;
import examples.hibernate.domain.User;

public class UserConsole {
	/**
	 * The composite id class must implement {@link Serializable}
	 */
	public static class Id implements Serializable{
		private static final long serialVersionUID = 6957111246277664938L;
		private int userId;
		private int consoleId;
		
		public Id(){
			
		}
		public Id(int userId, int consoleId) {
			super();
			this.userId = userId;
			this.consoleId = consoleId;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + consoleId;
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
			if (consoleId != other.consoleId)
				return false;
			if (userId != other.userId)
				return false;
			return true;
		}
		
	}
	
	private Id id = new Id();
	private User user;
	private Console console;
	private Date acquiredDate;
	private String acquiredCondition;
	
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
	public Console getConsole() {
		return console;
	}
	public void setConsole(Console console) {
		this.console = console;
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
	
}
