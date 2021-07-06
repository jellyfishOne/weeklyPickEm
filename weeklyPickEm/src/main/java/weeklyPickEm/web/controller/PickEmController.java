package weeklyPickEm.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import weeklyPickEm.web.model.SeasonMatchesDto;

@RestController
@RequestMapping("api/v1/pickem")
public class PickEmController {
	
	@GetMapping("/season-matches")
	public void getSeasonMatches() {
		
	}

}
