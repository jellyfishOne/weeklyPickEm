package weeklyPickEm;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
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
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import weeklyPickEm.repositories.MatchesRepository;
import weeklyPickEm.web.controller.PickEmController;
import weeklyPickEm.web.model.SeasonMatchesDto;
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
	
	@Mock 
	SeasonMatchesDto seasonMatches = SeasonMatchesDto.builder().build();
	
	@Test
	void getSeasonMatches() throws Exception{
		List<SeasonMatchesDto> seasonMatchesList = new ArrayList<SeasonMatchesDto>();
		seasonMatchesList.add(seasonMatches);
		
		given(matchesRepository.findAll()).willReturn(seasonMatchesList);
		
		FieldDescriptor[] seasonMatches = new FieldDescriptor[] {
				fieldWithPath("id").description("Season Id").type("String").optional(),
				fieldWithPath("userName").description("User Name").type("String").optional() };
		
		mockMvc.perform(get("/api/v1/pickem/season-matches")
						.accept(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk())
						.andDo(document("v1/get-season-matches",
								responseFields(
										fieldWithPath("[]").description("ArrayList of matches"))
											.andWithPrefix("[].", seasonMatches)
										)).andDo(MockMvcResultHandlers.print());
		
	}
}
