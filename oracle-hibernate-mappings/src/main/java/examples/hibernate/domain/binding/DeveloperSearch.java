package examples.hibernate.domain.binding;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import examples.hibernate.domain.Developer;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "http://domain.examples")
@XmlRootElement(name = "developers", namespace = "http://domain.examples")
public class DeveloperSearch {
	@XmlElement(name = "developer", required = true)
	List<Developer> developers = new ArrayList<Developer>();
	
	public DeveloperSearch(){
		
	}

	public DeveloperSearch(List<Developer> developers) {
		super();
		this.developers = developers;
	}

	public List<Developer> getDevelopers() {
		return developers;
	}

	public void setDevelopers(List<Developer> developers) {
		this.developers = developers;
	}
		
}
