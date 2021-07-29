package weeklyPickEm.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import weeklyPickEm.web.model.SeasonMatchesDto;

@Repository
public interface MatchesRepository extends MongoRepository<SeasonMatchesDto, String>{
	
	Optional<SeasonMatchesDto> findBySeasonYear(String SeasonYear);
	Optional<SeasonMatchesDto> findById(String Id);

}
