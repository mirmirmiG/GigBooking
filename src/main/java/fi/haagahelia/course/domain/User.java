package fi.haagahelia.course.domain;

import java.util.List;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;
	
	// Username with unique constraint
	@Column(name = "username", nullable = false, unique = true)
	private String username;
	@Column(name = "password", nullable = false)
	private String passwordHash;
	@Column(name ="email", nullable = false)
	private String email;
	@Column(name = "role", nullable = false)
	private String role;
	
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(
			name="user_gig",
			joinColumns= {@JoinColumn(name ="userid")},
			inverseJoinColumns = { @JoinColumn(name = "gigid") })
	 private Set<Gig> gigs;
	
	public User (){}
	
	public User(String username, String passwordHash, String email,String role) {
		super();
		this.username = username;
		this.passwordHash = passwordHash;
		this.email = email;
		this.role = role;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPasswordHash() {
		return passwordHash;
	}
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}	
	
	public Set<Gig> getGigs() {
        return gigs;
    }
     
    public void setGigs(Set<Gig> gigs) {
        this.gigs = gigs;
    }


	
	public boolean hasGig(Gig gig) {
		for (Gig booking: getGigs()) {
			if (booking.getId() == gig.getId()) {
				return true;
			}
		}
		return false;
	}	
		
}
