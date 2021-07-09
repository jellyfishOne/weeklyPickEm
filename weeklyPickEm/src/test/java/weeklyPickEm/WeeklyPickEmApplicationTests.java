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
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import weeklyPickEm.web.model.WeeklyMatches;
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
	
	
	@Test
	void getSeasonMatches() throws Exception{
		Match match = Match.builder()
			.teamOne("TeamONe")
			.teamOneScore(21)
			.teamTwo("TeamTwo")
			.teamTwoScore(17)
			.build();
		
		List<Match> matchList = new ArrayList<Match>();
		matchList.add(match);
		
		WeeklyMatches weeklyMatches = WeeklyMatches.builder()
		.matchWeek(1)
		.matches(matchList)
		.weekMatchesGuessedRight(4)
		.weekMatchesGuessedWrong(2)
		.build();
		
		List<WeeklyMatches> weeklyMatchesList = new ArrayList<WeeklyMatches>();
		weeklyMatchesList.add(weeklyMatches);
		
	
		SeasonMatchesDto seasonMatchesDto = SeasonMatchesDto.builder()
		.seasonYear("2021")
		.weeklyMatches(weeklyMatchesList)
		.totalCorrectPicks(2)
		.totalWrongPicks(3)
		.build();
		
		given(services.getSeason(any())).willReturn(Optional.of(seasonMatchesDto));
		
		mockMvc.perform(get("/api/v1/pickem/season-matches/{Id}", "2021")
						.accept(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk())
						.andDo(document("v1/season-matches",
								pathParameters(
											parameterWithName("Id").description("Id of desired season")
										),
								responseFields(
										fieldWithPath("seasonYear").type("String").description("Year of season"))
										)).andDo(MockMvcResultHandlers.print());
		
	}
}
