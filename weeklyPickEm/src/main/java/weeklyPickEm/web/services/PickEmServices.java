package weeklyPickEm.web.services;

import java.util.List;

import weeklyPickEm.web.model.SeasonMatchesDto;

public interface PickEmServices {
	List<SeasonMatchesDto> getSeasonMatches();

}
