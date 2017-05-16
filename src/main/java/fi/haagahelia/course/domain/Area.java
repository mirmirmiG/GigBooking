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
public class Area {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long areaid;
	private String areaName;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "area")
	private List<Gig> gigs;
	
	public Area(){};
	
	public Area(String areaName) {
		super();
		this.areaName = areaName;
	}

	public Long getAreaid() {
		return areaid;
	}

	public void setVenueid(Long areaid) {
		this.areaid = areaid;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public List<Gig> getGigs() {
		return gigs;
	}

	public void setGigs(List<Gig> gigs) {
		this.gigs = gigs;
	}

	@Override
	public String toString() {
		return this.areaName;
	}
	
}
