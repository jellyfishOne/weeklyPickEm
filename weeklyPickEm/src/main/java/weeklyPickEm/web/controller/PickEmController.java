package weeklyPickEm.web.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import weeklyPickEm.web.model.SeasonMatchesDto;
import weeklyPickEm.web.services.PickEmServices;

@RestController
@RequestMapping("api/v1/pickem")
public class PickEmController {
	
	@Autowired
	PickEmServices pickEmService;

	@GetMapping("/season-matches/{Id}")
	public ResponseEntity<Optional<SeasonMatchesDto>> getSeasonMatches(@PathVariable("Id") String Id) {
		Optional<SeasonMatchesDto> seasonMatches = pickEmService.getSeason(Id);
		return ResponseEntity.status(HttpStatus.OK).body(seasonMatches);
	}
}
