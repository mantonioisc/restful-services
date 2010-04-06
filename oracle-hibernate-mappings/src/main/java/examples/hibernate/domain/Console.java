package examples.hibernate.domain;

public class Console {
	private int id;
	private String name;
	private String fullName;
	private String description;
	private double price;
	private int maxPlayers;
	private boolean isWireless;
	private boolean hasNetworkConnection;
	private boolean isHighDefinition;
	private String media;
	private Company company;
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getMaxPlayers() {
		return maxPlayers;
	}
	public void setMaxPlayers(int maxPlayers) {
		this.maxPlayers = maxPlayers;
	}
	public boolean isWireless() {
		return isWireless;
	}
	public void setWireless(boolean isWireless) {
		this.isWireless = isWireless;
	}
	public boolean isHasNetworkConnection() {
		return hasNetworkConnection;
	}
	public void setHasNetworkConnection(boolean hasNetworkConnection) {
		this.hasNetworkConnection = hasNetworkConnection;
	}
	public boolean isHighDefinition() {
		return isHighDefinition;
	}
	public void setHighDefinition(boolean isHighDefinition) {
		this.isHighDefinition = isHighDefinition;
	}
	public String getMedia() {
		return media;
	}
	public void setMedia(String media) {
		this.media = media;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public int getId() {
		return id;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ ((fullName == null) ? 0 : fullName.hashCode());
		result = prime * result + (hasNetworkConnection ? 1231 : 1237);
		result = prime * result + id;
		result = prime * result + (isHighDefinition ? 1231 : 1237);
		result = prime * result + (isWireless ? 1231 : 1237);
		result = prime * result + maxPlayers;
		result = prime * result + ((media == null) ? 0 : media.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Console other = (Console) obj;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (fullName == null) {
			if (other.fullName != null)
				return false;
		} else if (!fullName.equals(other.fullName))
			return false;
		if (hasNetworkConnection != other.hasNetworkConnection)
			return false;
		if (id != other.id)
			return false;
		if (isHighDefinition != other.isHighDefinition)
			return false;
		if (isWireless != other.isWireless)
			return false;
		if (maxPlayers != other.maxPlayers)
			return false;
		if (media == null) {
			if (other.media != null)
				return false;
		} else if (!media.equals(other.media))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(price) != Double
				.doubleToLongBits(other.price))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Console [company=" + company + ", description=" + description
				+ ", fullName=" + fullName + ", hasNetworkConnection="
				+ hasNetworkConnection + ", id=" + id + ", isHighDefinition="
				+ isHighDefinition + ", isWireless=" + isWireless
				+ ", maxPlayers=" + maxPlayers + ", media=" + media + ", name="
				+ name + ", price=" + price + "]";
	}
	
}
