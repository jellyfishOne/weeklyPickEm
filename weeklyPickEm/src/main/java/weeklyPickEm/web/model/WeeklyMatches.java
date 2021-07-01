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
	private int matchWeek;
	private List<Match> matches;
	private int weekMatchesGuessedRight;
	private int weekMatchesGuessedWrong;
	
	
	@JsonFormat(pattern="MM-dd-yyyy HH:mm:ss")
	private LocalDateTime lastUpdate;

}
