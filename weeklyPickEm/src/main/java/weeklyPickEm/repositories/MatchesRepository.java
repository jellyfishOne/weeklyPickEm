package weeklyPickEm.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import weeklyPickEm.web.model.AllMatchesForTheWeek;
import weeklyPickEm.web.model.SeasonMatchesDto;

@Repository
public interface MatchesRepository extends MongoRepository<SeasonMatchesDto, String>{
	
	Optional<SeasonMatchesDto> findBySeasonYear(String seasonYear);
	Optional<SeasonMatchesDto> findById(String Id);
	
	//"seasonYear":"2021","weeklyMatches":[{"matchWeek":1,"matches": [{"teamOne":"TeamONe"...
	@Query("{'seasonYear': ?0, 'weeklyMatches.matchWeek' : ?0}")
	List<AllMatchesForTheWeek> findBySeasonyearAndWeeklyMatchesMatchWeek(String seasonYear, int matchWeek);
	
}
