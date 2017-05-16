package fi.haagahelia.course.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface GigRepository extends CrudRepository<Gig, Long> {
	List<Gig> findByTitle(String title);

}
