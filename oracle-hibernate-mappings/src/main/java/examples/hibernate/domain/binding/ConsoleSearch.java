package examples.hibernate.domain.binding;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import examples.hibernate.domain.Console;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "http://domain.examples")
@XmlRootElement(name = "consoles", namespace = "http://domain.examples")
public class ConsoleSearch {
	@XmlElement(name = "console", required = true)
	private List<Console> consoles = new ArrayList<Console>();
	
	public ConsoleSearch(){
		
	}

	public ConsoleSearch(List<Console> consoles) {
		super();
		this.consoles = consoles;
	}

	public List<Console> getConsoles() {
		return consoles;
	}

	public void setConsoles(List<Console> consoles) {
		this.consoles = consoles;
	}
		
}
