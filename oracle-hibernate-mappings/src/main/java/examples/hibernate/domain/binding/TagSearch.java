package examples.hibernate.domain.binding;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import examples.hibernate.domain.Tag;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "http://domain.examples")
@XmlRootElement(name = "tags", namespace = "http://domain.examples")
public class TagSearch {
	@XmlElement(name = "tag", required = true)
	private List<Tag> tags;
	
	public TagSearch(){
		tags = new ArrayList<Tag>();
	}

	public TagSearch(List<Tag> tags) {
		super();
		this.tags = tags;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
}
