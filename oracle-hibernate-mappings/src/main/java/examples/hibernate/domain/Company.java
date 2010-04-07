package examples.hibernate.domain;

public class Company {
	private int id;
	private String name;
	private String fullName;
	private String country;
	private String webSite;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getWebSite() {
		return webSite;
	}
	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}
	private void setId(int id){
		this.id = id;
	}
	public int getId() {
		return id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result
				+ ((fullName == null) ? 0 : fullName.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((webSite == null) ? 0 : webSite.hashCode());
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
		Company other = (Company) obj;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (fullName == null) {
			if (other.fullName != null)
				return false;
		} else if (!fullName.equals(other.fullName))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (webSite == null) {
			if (other.webSite != null)
				return false;
		} else if (!webSite.equals(other.webSite))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Company [country=" + country + ", fullName=" + fullName
				+ ", id=" + id + ", name=" + name + ", webSite=" + webSite
				+ "]";
	}
	
}
