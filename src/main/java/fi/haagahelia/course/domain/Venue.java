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
public class Venue {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long venueid;
	private String venueName;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "venue")
	private List<Gig> gigs;
	
	public Venue(){};
	
	public Venue(String venueName) {
		super();
		this.venueName = venueName;
	}

	public Long getVenueid() {
		return venueid;
	}

	public void setVenueid(Long venueid) {
		this.venueid = venueid;
	}

	public String getVenueName() {
		return venueName;
	}

	public void setVenueName(String venueName) {
		this.venueName = venueName;
	}

	public List<Gig> getGigs() {
		return gigs;
	}

	public void setGigs(List<Gig> gigs) {
		this.gigs = gigs;
	}

	@Override
	public String toString() {
		return this.venueName;
	}
	
}
