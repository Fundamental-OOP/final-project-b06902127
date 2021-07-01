package scoring;

import action.WinningTiles;
import tiles.WindRank;

import java.util.ArrayList;

public class SequentialWin extends ScoringPattern {
	public SequentialWin(int sequentialWinCount) {
		super("SequentialWin", 2 * sequentialWinCount + 1, new ArrayList<String>());
	}

	@Override
	public boolean patternMatch(WinningTiles winningTiles, boolean isSelfPick, WindRank prevailingWind, WindRank playerWind) {
		return true;
	}

}