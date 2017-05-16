package fi.haagahelia.course.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;


public interface AreaRepository extends CrudRepository<Area, Long>{
	List<Area> findByAreaName(String areaName);
}
