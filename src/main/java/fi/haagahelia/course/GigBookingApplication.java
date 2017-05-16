package fi.haagahelia.course;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.course.domain.Area;
import fi.haagahelia.course.domain.AreaRepository;
import fi.haagahelia.course.domain.Gig;
import fi.haagahelia.course.domain.GigRepository;
import fi.haagahelia.course.domain.ArtistRepository;
import fi.haagahelia.course.domain.Artist;
import fi.haagahelia.course.domain.User;
import fi.haagahelia.course.domain.UserRepository;
import fi.haagahelia.course.domain.Venue;
import fi.haagahelia.course.domain.VenueRepository;

@SpringBootApplication
public class GigBookingApplication {
	private static final Logger log = LoggerFactory.getLogger(GigBookingApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(GigBookingApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner gigDemo(GigRepository grepository, ArtistRepository arepository, VenueRepository vrepository, AreaRepository rrepository, UserRepository urepository){
		return (args) -> {
			log.info("save a couple of artists");
			arepository.save(new Artist("Dragonazam"));
			arepository.save(new Artist("Hitmasect"));
			arepository.save(new Artist("Dappere Troubadour"));
			arepository.save(new Artist("Moving Piggy"));
			arepository.save(new Artist("Kei Verkouwe"));
						
			log.info("save a couple of venues");
			vrepository.save(new Venue("BizBash"));
			vrepository.save(new Venue("Estates"));
			vrepository.save(new Venue("Grand Marquis"));
			vrepository.save(new Venue("Imperia"));
			vrepository.save(new Venue("The Backyard"));

			log.info("save a couple of areas");
			rrepository.save(new Area("Töölö"));
			rrepository.save(new Area("Leppävaara"));
			rrepository.save(new Area("Malmi"));
			rrepository.save(new Area("Siltamaki"));
							
			grepository.save(new Gig("Herrie achter in de bouwplaats", "AUG 19", 50, arepository.findByArtistName("Dragonazam").get(0), vrepository.findByVenueName("The Backyard").get(0), rrepository.findByAreaName("Töölö").get(0)));	
			grepository.save(new Gig("K.O.", "JUN 30", 100, arepository.findByArtistName("Hitmasect").get(0), vrepository.findByVenueName("Imperia").get(0), rrepository.findByAreaName("Töölö").get(0)));
			grepository.save(new Gig("Screaming robots", "OCT 14", 35, arepository.findByArtistName("Moving Piggy").get(0), vrepository.findByVenueName("Grand Marquis").get(0), rrepository.findByAreaName("Leppävaara").get(0)));
			grepository.save(new Gig("Minimalfunction", "SEP 09", 48, arepository.findByArtistName("Moving Piggy").get(0), vrepository.findByVenueName("Estates").get(0), rrepository.findByAreaName("Malmi").get(0)));
			grepository.save(new Gig("Noise in Skinny jeans", "DEC 26", 28, arepository.findByArtistName("Dappere Troubadour").get(0), vrepository.findByVenueName("Estates").get(0), rrepository.findByAreaName("Siltamaki").get(0)));
			grepository.save(new Gig("Winning cowboys", "JUL 04", 57, arepository.findByArtistName("Hitmasect").get(0), vrepository.findByVenueName("BizBash").get(0), rrepository.findByAreaName("Malmi").get(0)));
			grepository.save(new Gig("Dust beyond Neptune", "MAY 11", 80, arepository.findByArtistName("Moving Piggy").get(0), vrepository.findByVenueName("Estates").get(0), rrepository.findByAreaName("Leppävaara").get(0)));
			grepository.save(new Gig("Trippin monkeys", "AUG 15", 60, arepository.findByArtistName("Kei Verkouwe").get(0), vrepository.findByVenueName("Estates").get(0), rrepository.findByAreaName("Töölö").get(0)));
			
			
			log.info("fetch all gigs");
			for (Gig gig : grepository.findAll()){
				log.info(gig.toString());
			}
			// Create users: admin/admin user/user
			User user1 = new User("user", "$2a$06$KN9m9RSzZAjNms4..9TuJ.7dikAqUcAiei7/tA1TK3esvCa47q4NS","user1@book.com", "ROLE_USER");
			User user2 = new User("admin", "$2a$06$L7Et7s3kykIXfvRULrYfE.KEeZWwSgn7qExpmN6Dn2NN18xKw.cT6","user2@book.com", "ROLE_ADMIN");
			urepository.save(user1);
			urepository.save(user2);
			
		
			
			
		};
	}
}
