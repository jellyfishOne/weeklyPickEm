package weeklyPickEm.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Match {
	private String teamOne;
	private String teamTwo;
	private boolean teamOnePicked;
	private boolean teamTwoPicked;
	private int teamOneScore;
	private int teamTwoScore;

}
