package fi.haagahelia.course;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import fi.haagahelia.course.domain.AreaRepository;
import fi.haagahelia.course.domain.ArtistRepository;
import fi.haagahelia.course.domain.Gig;
import fi.haagahelia.course.domain.GigRepository;
import fi.haagahelia.course.domain.VenueRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class GigRepositoryTest {

	@Autowired
	private GigRepository grepository;
	@Autowired
	private ArtistRepository arepository;
	@Autowired
	private VenueRepository vrepository;
	@Autowired
	private AreaRepository rrepository;
	
	@Test
	public void findByTitleShouldReturnGig(){
		List<Gig> gigs= grepository.findByTitle("Minimalfunction");
		assertThat(gigs).hasSize(1);
		assertThat(gigs.get(0).getArtist()).isEqualTo("Moving Piggy");
	}
	@Test
	public void createNewGig(){
		Gig gig = new Gig("Gig", "AUG 09", 17, arepository.findByArtistName("Hitmasect").get(0), vrepository.findByVenueName("Imperia").get(0),rrepository.findByAreaName("Siltamaki").get(0));
		grepository.save(gig);
		assertThat(gig.getId()).isNotNull();
	}
}
