package scoring;

import action.WinningTiles;
import tiles.WindRank;

public interface ScoringHandler {
	public abstract int scoring(WinningTiles winningTiles, boolean isSelfPick, WindRank prevailingWind, WindRank playerWind, int sequentialWinCount);
}