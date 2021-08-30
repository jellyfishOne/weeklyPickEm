package weeklyPickEm.web.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import weeklyPickEm.repositories.MatchesRepository;
import weeklyPickEm.web.model.AllMatchesForTheWeek;
import weeklyPickEm.web.model.SeasonMatchesDto;

@Service
public class PickEmServicesImpl implements PickEmServices{
	
	@Autowired
	MatchesRepository matchesRepo;

	@Override
	public Optional<SeasonMatchesDto> getSeason(String Id) {
		 return matchesRepo.findById(Id);
		
	}

	@Override
	public Optional<SeasonMatchesDto> getSeasonByYear(String seasonYear) {
		return matchesRepo.findBySeasonYear(seasonYear);
	}

	@Override
	public List<AllMatchesForTheWeek> getAllWeekMatchesByYear(String seasonYear, Integer matchWeek) {
		return matchesRepo.findBySeasonyearAndWeeklyMatchesMatchWeek(seasonYear, matchWeek);
	}
}
