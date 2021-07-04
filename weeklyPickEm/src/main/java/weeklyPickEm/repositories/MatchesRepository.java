package weeklyPickEm.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import weeklyPickEm.web.model.SeasonMatchesDto;

@Repository
public interface MatchesRepository extends MongoRepository<SeasonMatchesDto, String>{

}
