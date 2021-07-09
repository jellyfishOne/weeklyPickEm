package weeklyPickEm.web.services;

import java.util.List;
import java.util.Optional;

import weeklyPickEm.web.model.SeasonMatchesDto;

public interface PickEmServices {
	Optional<SeasonMatchesDto> getSeason(String Id);

}
