package fi.haagahelia.course.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Artist {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long artistid;
	private String artistName;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "artist")
	private List<Gig> gigs;
	
	public Artist(){};
	
	public Artist(String artistName) {
		super();
		this.artistName = artistName;
	}

	public Long getArtistid() {
		return artistid;
	}

	public void setArtistid(Long artistid) {
		this.artistid = artistid;
	}

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	public List<Gig> getGigs() {
		return gigs;
	}

	public void setGigs(List<Gig> gigs) {
		this.gigs = gigs;
	}

	@Override
	public String toString() {
		return this.artistName;
	}

	
	

	
	
	
}
