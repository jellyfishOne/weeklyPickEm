package weeklyPickEm;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;

import weeklyPickEm.repositories.MatchesRepository;
import weeklyPickEm.web.controller.PickEmController;
import weeklyPickEm.web.model.SeasonMatchesDto;

@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs
@WebMvcTest(PickEmController.class)
class WeeklyPickEmApplicationTests {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	MatchesRepository matchesRepository;
	
	@Mock 
	SeasonMatchesDto seasonMatches = SeasonMatchesDto.builder().build();
	
	
	
	@Test
	void getSeasonMatches() throws Exception{
		List<SeasonMatchesDto> seasonMatchesList = new ArrayList<SeasonMatchesDto>();
		seasonMatchesList.add(seasonMatches);
		seasonMatchesList.add(seasonMatches);
		
		given(matchesRepository.findAll()).willReturn(seasonMatchesList);
		
		mockMvc.perform(get("/api/v1/pickem/season-matches")
						.accept(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk());
	}
}
