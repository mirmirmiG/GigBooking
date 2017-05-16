package fi.haagahelia.course.domain;

import java.util.Set;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Gig {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String title;
	private String date;
	private int price;
	
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "artistid")
	private Artist artist;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name= "venueid")
	private Venue venue;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name= "areaid")
	private Area area;
	
	@ManyToMany(mappedBy = "gigs")    
	 private Set<User> users;  
	    
	public Gig(){}
	
	public Gig(String title, String date, int price, Artist artist, Venue venue, Area area) {
		super();
		this.title = title;
		this.date = date;
		this.price = price;
		this.artist = artist;
		this.venue = venue;
		this.area = area;
		
	}

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public Venue getVenue() {
		return venue;
	}

	public void setVenue(Venue venue) {
		this.venue = venue;
	}
	
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}
	
	public Set<User> getUsers() {
    return users;
    }
     
    public void setUsers(Set<User> users) {
        this.users = users;
    }
	@Override
	public String toString() {
		if(this.artist != null & this.venue !=null & this.area !=null)
		return "Gig [id=" + id + ", title=" + title + ", date=" + date
				+ ", price=" + price + ", artist=" + this.getArtist().getArtistName() + ", venue=" + this.getVenue().getVenueName() + ", area=" + this.getArea().getAreaName() +"]";
		else 
		return	"Gig [id=" + id + ", title=" + title + ", date=" + date
			+ ", price=" + price + "]";
	}

	
	 

}
