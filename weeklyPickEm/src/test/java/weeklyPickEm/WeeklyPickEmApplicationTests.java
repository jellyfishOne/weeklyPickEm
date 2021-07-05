package weeklyPickEm;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import weeklyPickEm.repositories.MatchesRepository;
import weeklyPickEm.web.model.Match;
import weeklyPickEm.web.model.SeasonMatchesDto;
import weeklyPickEm.web.model.WeeklyMatches;
import weeklyPickEm.web.services.PickEmServices;

@SpringBootTest
class WeeklyPickEmApplicationTests {
	
	@Autowired
	private MatchesRepository matchesRepo;
	
	@Autowired
	private PickEmServices pickemServices;

	@Test
	void testGetSeasonMatches() {
		Match matchOne = Match.builder()
				.teamOne("team A")
				.teamTwo("team B")
				.teamPicked("team A")
				.teamOneScore(21)
				.teamTwoScore(14)
				.build();
		Match matchTwo = Match.builder()
				.teamOne("team 1A")
				.teamTwo("team 1B")
				.teamPicked("team 1B")
				.teamOneScore(21)
				.teamTwoScore(14)
				.build();
		List<Match> matches =  new ArrayList<Match>();
		matches.add(matchOne);
		matches.add(matchTwo);
		
		
		WeeklyMatches weekOneMatches = WeeklyMatches.builder()
				.matchWeek(1)
				.matches(matches)
				.weekMatchesGuessedRight(2)
				.weekMatchesGuessedWrong(0)
				.lastUpdate(LocalDateTime.now())
				.build();
		
		List<WeeklyMatches> weeklySeasonMatches = new ArrayList<WeeklyMatches>();
		weeklySeasonMatches.add(weekOneMatches);
	
		SeasonMatchesDto seasonMatches = SeasonMatchesDto.builder()
				.userName("JellyFish")
				.seasonYear(2021)
				.weeklyMatches(weeklySeasonMatches)
				.totalCorrectPicks(0)
				.totalWrongPicks(1)
				.bestPickEmWeek(1)
				.worstPickEmWeek(2)
				.build();
		
		matchesRepo.save(seasonMatches);
		List<SeasonMatchesDto> season = matchesRepo.findAll();
		assertNotNull(season);
		
	}

}
