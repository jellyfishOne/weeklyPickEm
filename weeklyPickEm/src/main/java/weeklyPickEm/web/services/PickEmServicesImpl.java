package weeklyPickEm.web.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import weeklyPickEm.repositories.MatchesRepository;
import weeklyPickEm.web.model.Match;
import weeklyPickEm.web.model.SeasonMatchesDto;
import weeklyPickEm.web.model.WeeklyMatches;

@Service
public class PickEmServicesImpl implements PickEmServices{
	
	@Autowired
	MatchesRepository matchesRepo;

	@Override
	public List<SeasonMatchesDto> getSeasonMatches() {	
		 return matchesRepo.findAll();
		
	}
}
