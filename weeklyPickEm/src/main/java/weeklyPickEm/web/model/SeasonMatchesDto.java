package weeklyPickEm.web.model;

import java.util.List;

import javax.validation.constraints.Null;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document(collection = "seasonMatches")
public class SeasonMatchesDto {
	@Id
	@Null
	private String id;
	private String userName;
	private Integer seasonYear;
	private List<WeeklyMatches> weeklyMatches;
	
	private Integer totalCorrectPicks;
	private Integer totalWrongPicks;
	
	private Integer bestPickEmWeek;
	private Integer worstPickEmWeek;
	

}
