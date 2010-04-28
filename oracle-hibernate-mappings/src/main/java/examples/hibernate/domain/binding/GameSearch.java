package examples.hibernate.domain.binding;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import examples.hibernate.domain.Game;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "http://domain.examples")
@XmlRootElement(name = "games", namespace = "http://domain.examples")
public class GameSearch {
	@XmlElement(name = "game", required = true)
	List<Game> games = new ArrayList<Game>();
	
	public GameSearch(){
		
	}

	public GameSearch(List<Game> games) {
		super();
		this.games = games;
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}
		
}
