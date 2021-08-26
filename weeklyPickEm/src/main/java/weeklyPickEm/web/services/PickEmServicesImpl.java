package weeklyPickEm.web.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import weeklyPickEm.repositories.MatchesRepository;
import weeklyPickEm.web.model.Match;
import weeklyPickEm.web.model.SeasonMatchesDto;
import weeklyPickEm.web.model.AllMatchesForTheWeek;

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
}
