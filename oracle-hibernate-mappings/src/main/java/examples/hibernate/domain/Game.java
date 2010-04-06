package examples.hibernate.domain;

public class Game {
	/**
	 * This will be the key, is a natural key
	 * composed of 4 letters, and 5 digits
	 */
	private String code;
	private String title;
	private String description;
	private String media;
	private int releasedYear;
	private double price;
	private int playersNumber;
	private String storageSpace;
	private char rate;
	private double averageRaiting;
	private Developer developer;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMedia() {
		return media;
	}
	public void setMedia(String media) {
		this.media = media;
	}
	public int getReleasedYear() {
		return releasedYear;
	}
	public void setReleasedYear(int releasedYear) {
		this.releasedYear = releasedYear;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getPlayersNumber() {
		return playersNumber;
	}
	public void setPlayersNumber(int playersNumber) {
		this.playersNumber = playersNumber;
	}
	public String getStorageSpace() {
		return storageSpace;
	}
	public void setStorageSpace(String storageSpace) {
		this.storageSpace = storageSpace;
	}
	public char getRate() {
		return rate;
	}
	public void setRate(char rate) {
		this.rate = rate;
	}
	public double getAverageRaiting() {
		return averageRaiting;
	}
	public void setAverageRaiting(double averageRaiting) {
		this.averageRaiting = averageRaiting;
	}
	public Developer getDeveloper() {
		return developer;
	}
	public void setDeveloper(Developer developer) {
		this.developer = developer;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(averageRaiting);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ ((developer == null) ? 0 : developer.hashCode());
		result = prime * result + ((media == null) ? 0 : media.hashCode());
		result = prime * result + playersNumber;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + rate;
		result = prime * result + releasedYear;
		result = prime * result
				+ ((storageSpace == null) ? 0 : storageSpace.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Game other = (Game) obj;
		if (Double.doubleToLongBits(averageRaiting) != Double
				.doubleToLongBits(other.averageRaiting))
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (developer == null) {
			if (other.developer != null)
				return false;
		} else if (!developer.equals(other.developer))
			return false;
		if (media == null) {
			if (other.media != null)
				return false;
		} else if (!media.equals(other.media))
			return false;
		if (playersNumber != other.playersNumber)
			return false;
		if (Double.doubleToLongBits(price) != Double
				.doubleToLongBits(other.price))
			return false;
		if (rate != other.rate)
			return false;
		if (releasedYear != other.releasedYear)
			return false;
		if (storageSpace == null) {
			if (other.storageSpace != null)
				return false;
		} else if (!storageSpace.equals(other.storageSpace))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Game [averageRaiting=" + averageRaiting + ", code=" + code
				+ ", description=" + description + ", developer=" + developer
				+ ", media=" + media + ", playersNumber=" + playersNumber
				+ ", price=" + price + ", rate=" + rate + ", releasedYear="
				+ releasedYear + ", storageSpace=" + storageSpace + ", title="
				+ title + "]";
	}
	
}
