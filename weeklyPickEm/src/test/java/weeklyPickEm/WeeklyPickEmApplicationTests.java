package weeklyPickEm;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import weeklyPickEm.repositories.MatchesRepository;
import weeklyPickEm.web.controller.PickEmController;
import weeklyPickEm.web.model.Match;
import weeklyPickEm.web.model.SeasonMatchesDto;
import weeklyPickEm.web.model.AllMatchesForTheWeek;
import weeklyPickEm.web.services.PickEmServices;

@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs
@WebMvcTest(PickEmController.class)
class WeeklyPickEmApplicationTests {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	MatchesRepository matchesRepository;
	
	@MockBean 
	PickEmServices services;
	
	SeasonMatchesDto seasonMatchesDto;
	
	@BeforeEach
	void init() {
		Match match = Match.builder()
				.teamOne("TeamONe")
				.teamOneScore(21)
				.teamTwo("TeamTwo")
				.teamTwoScore(17)
				.build();
			
			List<Match> matchList = new ArrayList<Match>();
			matchList.add(match);
			
			AllMatchesForTheWeek weeklyMatches = AllMatchesForTheWeek.builder()
			.matchWeek(1)
			.matches(matchList)
			.weekMatchesGuessedRight(4)
			.weekMatchesGuessedWrong(2)
			.build();
			
			List<AllMatchesForTheWeek> weeklyMatchesList = new ArrayList<AllMatchesForTheWeek>();
			weeklyMatchesList.add(weeklyMatches);
			
			this.seasonMatchesDto = SeasonMatchesDto.builder()
			.seasonYear("2021")
			.weeklyMatches(weeklyMatchesList)
			.totalCorrectPicks(2)
			.totalWrongPicks(3)
			.build();
	}

	@Test
	void getSeasonMatches() throws Exception{	
		given(services.getSeason(any())).willReturn(Optional.of(seasonMatchesDto));
		
		mockMvc.perform(get("/api/v1/pickem/season-matches/{Id}", "2021")
						.accept(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk())
						.andDo(document("v1/season-matches",
								pathParameters(
											parameterWithName("Id").description("Id of desired season")
										),
								responseFields(
										fieldWithPath("id").type("String").description("Id of desired season"),
										fieldWithPath("seasonYear").type("String").description("Year of season"),
										fieldWithPath("weeklyMatches[]").type("ArrayList").description("An ArrayList of Matches for each week of the season"),
										fieldWithPath("weeklyMatches[].matchWeek").type("Integer").description("Number of week in the season"),
										fieldWithPath("weeklyMatches[].matches[]").type("ArrayList").description("Matches for that given week"),
										fieldWithPath("weeklyMatches[].matches[].teamOne").type("String").description("One team of the match"),
										fieldWithPath("weeklyMatches[].matches[].teamTwo").type("String").description("Second team of the match"),
										fieldWithPath("weeklyMatches[].matches[].teamOneScore").type("Integer").description("Score of teamOne"),
										fieldWithPath("weeklyMatches[].matches[].teamTwoScore").type("Integer").description("Score of teamTwo"),
										fieldWithPath("weeklyMatches[].matches[].teamPicked").type("String").description("Team picked by the user"),
										fieldWithPath("weeklyMatches[].weekMatchesGuessedRight").type("Integer").description("Number of correct picks made for a given week"),
										fieldWithPath("weeklyMatches[].weekMatchesGuessedWrong").type("Integer").description("Number of wrong picks made for a given week"),
										fieldWithPath("weeklyMatches[].lastUpdate").type("LocalDateTime").description("Last time server updated scores"),
										fieldWithPath("totalCorrectPicks").type("Integer").description("Total number of correct picks for the season"),
										fieldWithPath("totalWrongPicks").type("Integer").description("Total number of wrong picks for the season")
										))).andDo(MockMvcResultHandlers.print());
	}
}
