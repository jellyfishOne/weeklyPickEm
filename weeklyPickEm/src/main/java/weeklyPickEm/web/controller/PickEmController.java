package weeklyPickEm.web.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import weeklyPickEm.web.model.AllMatchesForTheWeek;
import weeklyPickEm.web.model.SeasonMatchesDto;
import weeklyPickEm.web.services.PickEmServices;

@RestController
@RequestMapping("api/v1/pickem")
public class PickEmController {
	
	@Autowired
	PickEmServices pickEmService;

	/*
	 * TODO
	 * GET All matches for the week
	 * POST Submit picks for all matches in the week
	 * GET made picks for the week
	 * 
	 */
	
	@GetMapping("/season-matches/{Id}")
	public ResponseEntity<Optional<SeasonMatchesDto>> getSeasonMatches(@PathVariable("Id") String Id) {
		Optional<SeasonMatchesDto> seasonMatches = pickEmService.getSeason(Id);
		return ResponseEntity.status(HttpStatus.OK).body(seasonMatches);
	}
	
	@GetMapping("/season-matches-by-year/{seasonYear}")
	public ResponseEntity<Optional<SeasonMatchesDto>>  getSeasonMatchesByYear(@PathVariable("seasonYear")String seasonYear) {
		Optional<SeasonMatchesDto>  seasonMatches = pickEmService.getSeasonByYear(seasonYear);
		return ResponseEntity.status(HttpStatus.OK).body(seasonMatches);
	}
	
	@GetMapping("/week-matches-by-year/{seasonYear}/{matchWeek}")
	public ResponseEntity<List<AllMatchesForTheWeek>> getAllWeekMatchesByYear(@PathVariable("seasonYear")String seasonYear,
														@PathVariable("matchWeek")Integer matchWeek){
		List<AllMatchesForTheWeek> allMatchesForTheWeek = pickEmService.getAllWeekMatchesByYear(seasonYear, matchWeek);
		return ResponseEntity.status(HttpStatus.OK).body(allMatchesForTheWeek);
		
	}
	
	
	
}
