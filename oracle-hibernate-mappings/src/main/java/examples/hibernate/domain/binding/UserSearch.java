package examples.hibernate.domain.binding;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import examples.hibernate.domain.User;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "http://domain.examples")
@XmlRootElement(name = "users", namespace = "http://domain.examples")
public class UserSearch {
	@XmlElement(name = "user", required = true)
	List<User> users = new ArrayList<User>();

	public UserSearch() {
		
	}

	public UserSearch(List<User> users) {
		this.users = users;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}
