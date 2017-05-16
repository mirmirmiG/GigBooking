package fi.haagahelia.course;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import fi.haagahelia.course.web.GigController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GigBookingApplicationTests {
	@Autowired
	private GigController controller;
	
	@Test
	public void contextLoads() throws Exception{
		assertThat(controller).isNotNull();
	}

}
