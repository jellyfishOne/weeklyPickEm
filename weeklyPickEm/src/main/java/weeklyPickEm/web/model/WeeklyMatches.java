package weeklyPickEm.web.model;

import java.time.LocalDateTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class WeeklyMatches {
	private Integer matchWeek;
	private List<Match> matches;
	private Integer weekMatchesGuessedRight;
	private Integer weekMatchesGuessedWrong;
	
	
	@JsonFormat(pattern="MM-dd-yyyy HH:mm:ss")
	private LocalDateTime lastUpdate;

}
